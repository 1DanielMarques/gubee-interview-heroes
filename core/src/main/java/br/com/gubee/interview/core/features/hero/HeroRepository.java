package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.model.request.HeroRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class HeroRepository {

    private static final String CREATE_HERO_QUERY = " INSERT " +
            " INTO hero " +
            " (name, race, power_stats_id) " +
            " VALUES (:name, :race, :powerStatsId) RETURNING id ";

    private static final String FIND_HERO_BY_ID_QUERY = " SELECT " +
            "  hero.name, hero.race, " +
            "  power_stats.strength, power_stats.agility, power_stats.dexterity, " +
            "  power_stats.intelligence " +
            "  FROM hero INNER JOIN power_stats ON hero.power_stats_id = power_stats.id " +
            "  WHERE hero.id = :id ";

    private static final String FIND_HERO_BY_NAME_QUERY = " SELECT " +
            "  hero.name, hero.race, " +
            "  power_stats.strength, power_stats.agility, power_stats.dexterity, " +
            "  power_stats.intelligence " +
            "  FROM hero INNER JOIN power_stats ON hero.power_stats_id = power_stats.id " +
            "  WHERE hero.name = :name ";

    private static final String FIND_HERO_ATTRIBUTES_QUERY = "SELECT " +
            "  hero.id, hero.name, hero.race, " +
            "  power_stats.strength, power_stats.agility, power_stats.dexterity, " +
            "  power_stats.intelligence " +
            "  FROM hero INNER JOIN power_stats ON hero.power_stats_id = power_stats.id ";

    private static final String UPDATE_HERO_QUERY = " UPDATE " +
            " interview_service.hero " +
            " SET name = :name, " +
            " race = :race, " +
            " updated_at = :hero_updatedAt " +
            " WHERE hero.id = :hero_id; " +
            " UPDATE interview_service.power_stats " +
            " SET strength = :strength, " +
            " agility = :agility, " +
            " dexterity = :dexterity, " +
            " intelligence = :intelligence, " +
            " updated_at = :power_stats_updatedAt" +
            " WHERE power_stats.id = :power_stats_id ";
    private static final String DELETE_HERO_BY_ID_QUERY = " DELETE " +
            " FROM hero WHERE hero.id = :hero_id; " +
            " DELETE " +
            " FROM power_stats WHERE power_stats.id = :power_stats_id ";

    private final String GET_POWER_STATS_ID_QUERY = " SELECT " +
            " power_stats_id " +
            " FROM hero " +
            " WHERE hero.id = :hero_id ";

    private final String GET_HERO_ID_QUERY = " SELECT " +
            " id " +
            " FROM hero " +
            " WHERE hero.name = :hero_name ";

    private static final String GET_POWER_STATS_QUERY = " SELECT " +
            " strength, agility, dexterity, intelligence " +
            " FROM power_stats " +
            " WHERE id = :power_stats_id ";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    UUID create(Hero hero) {
        final Map<String, Object> params = Map.of("name", hero.getName(),
                "race", hero.getRace().name(),
                "powerStatsId", hero.getPowerStatsId());
        return namedParameterJdbcTemplate.queryForObject(
                CREATE_HERO_QUERY,
                params,
                UUID.class);
    }


    public List<HeroRequest> findAll() {
        List<HeroRequest> heroList = namedParameterJdbcTemplate.query(FIND_HERO_ATTRIBUTES_QUERY, new
                BeanPropertyRowMapper<>(HeroRequest.class));
        return heroList;
    }

    public HeroRequest findById(UUID id) {
        SqlParameterSource param = new MapSqlParameterSource("id", id);
        return namedParameterJdbcTemplate.queryForObject(FIND_HERO_BY_ID_QUERY, param, BeanPropertyRowMapper.newInstance(HeroRequest.class));
    }

    public HeroRequest findByName(String name) {
        SqlParameterSource param = new MapSqlParameterSource("name", name);
        return namedParameterJdbcTemplate.queryForObject(FIND_HERO_BY_NAME_QUERY, param, BeanPropertyRowMapper.newInstance(HeroRequest.class));
    }


    public void updateById(UUID id, HeroRequest heroRequest) {
        SqlParameterSource param = new MapSqlParameterSource("hero_id", id);
        UUID power_stats_id = namedParameterJdbcTemplate.queryForObject(GET_POWER_STATS_ID_QUERY, param, UUID.class);
        final Map<String, Object> params = Map.of("hero_id", id,
                "name", heroRequest.getName(),
                "race", heroRequest.getRace().name(),
                "strength", heroRequest.getStrength(),
                "agility", heroRequest.getAgility(),
                "dexterity", heroRequest.getDexterity(),
                "intelligence", heroRequest.getIntelligence(),
                "power_stats_id", power_stats_id,
                "hero_updatedAt", Timestamp.from(Instant.now()),
                "power_stats_updatedAt", Timestamp.from(Instant.now()));
        namedParameterJdbcTemplate.update(UPDATE_HERO_QUERY, params);
    }

    public void deleteById(UUID id) {
        SqlParameterSource param = new MapSqlParameterSource("hero_id", id);
        UUID power_stats_id = namedParameterJdbcTemplate.queryForObject(GET_POWER_STATS_ID_QUERY, param, UUID.class);
        final Map<String, Object> params = Map.of("hero_id", id, "power_stats_id", power_stats_id);
        namedParameterJdbcTemplate.update(DELETE_HERO_BY_ID_QUERY, params);
    }

    public UUID getHeroIdByName(String name) {
        SqlParameterSource param = new MapSqlParameterSource("hero_name", name);
        return namedParameterJdbcTemplate.queryForObject(GET_HERO_ID_QUERY, param, UUID.class);
    }

    public Map<UUID, PowerStats> compareHeroes(UUID id_1, UUID id_2) {
        UUID power_stats_id_1 = namedParameterJdbcTemplate.queryForObject(GET_POWER_STATS_ID_QUERY,
                new MapSqlParameterSource("hero_id", id_1), UUID.class);
        UUID power_stats_id_2 = namedParameterJdbcTemplate.queryForObject(GET_POWER_STATS_ID_QUERY,
                new MapSqlParameterSource("hero_id", id_2), UUID.class);
        PowerStats powerStats_1 = namedParameterJdbcTemplate.queryForObject(GET_POWER_STATS_QUERY,
                new MapSqlParameterSource("power_stats_id", power_stats_id_1), BeanPropertyRowMapper.newInstance(PowerStats.class));
        PowerStats powerStats_2 = namedParameterJdbcTemplate.queryForObject(GET_POWER_STATS_QUERY,
                new MapSqlParameterSource("power_stats_id", power_stats_id_2), BeanPropertyRowMapper.newInstance(PowerStats.class));
        return Map.of(id_1, powerStats_1, id_2, powerStats_2);
    }

}
