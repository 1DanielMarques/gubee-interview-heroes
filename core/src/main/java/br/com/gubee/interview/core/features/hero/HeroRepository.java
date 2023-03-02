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
import java.util.HashMap;
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

    private static final String DELETE_HERO_BY_ID_QUERY = " DELETE " +
            " FROM hero WHERE hero.id = :heroId; " +
            " DELETE " +
            " FROM power_stats WHERE power_stats.id = :powerStatsId ";

    private final String GET_POWER_STATS_ID_QUERY = " SELECT " +
            " power_stats_id " +
            " FROM hero " +
            " WHERE hero.id = :heroId ";

    private final String GET_HERO_ID_QUERY = " SELECT " +
            " id " +
            " FROM hero " +
            " WHERE hero.name = :heroName ";

    private static final String GET_POWER_STATS_QUERY = " SELECT " +
            " strength, agility, dexterity, intelligence " +
            " FROM power_stats " +
            " WHERE id = :powerStatsId ";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UUID create(Hero hero) {
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
        SqlParameterSource param = new MapSqlParameterSource("heroId", id);
        UUID powerStatsId = namedParameterJdbcTemplate.queryForObject(GET_POWER_STATS_ID_QUERY, param, UUID.class);
        final Map<String, Object> params = createSqlParams(heroRequest);
        params.put("heroId", id);
        params.put("powerStatsId", powerStatsId);
        params.put("heroUpdatedAt", Timestamp.from(Instant.now()));
        params.put("powerStatsUpdatedAt", Timestamp.from(Instant.now()));
        namedParameterJdbcTemplate.update(createUpdateQuery(heroRequest), params);
    }

    private String createUpdateQuery(HeroRequest heroRequest) {
        String UPDATE_HERO_QUERY = " UPDATE " +
                " interview_service.hero " +
                " SET ";
        UPDATE_HERO_QUERY += " updated_at = :heroUpdatedAt ";
        UPDATE_HERO_QUERY = (heroRequest.getName() != null) ? UPDATE_HERO_QUERY + ", name = :name " : UPDATE_HERO_QUERY + "";
        UPDATE_HERO_QUERY = (heroRequest.getRace() != null) ? UPDATE_HERO_QUERY + ", race = :race " : UPDATE_HERO_QUERY + "";
        UPDATE_HERO_QUERY += " WHERE hero.id = :heroId; ";
        UPDATE_HERO_QUERY += " UPDATE interview_service.power_stats SET ";
        UPDATE_HERO_QUERY += " updated_at = :powerStatsUpdatedAt  ";
        UPDATE_HERO_QUERY = (heroRequest.getStrength() != null && heroRequest.getStrength() >= 0 && heroRequest.getStrength() <= 10) ? UPDATE_HERO_QUERY + ", strength = :strength " : UPDATE_HERO_QUERY + "";
        UPDATE_HERO_QUERY = (heroRequest.getAgility() != null && heroRequest.getAgility() >= 0 && heroRequest.getAgility() <= 10) ? UPDATE_HERO_QUERY + ", agility = :agility " : UPDATE_HERO_QUERY + "";
        UPDATE_HERO_QUERY = (heroRequest.getDexterity() != null && heroRequest.getDexterity() >= 0 && heroRequest.getDexterity() <= 10) ? UPDATE_HERO_QUERY + ", dexterity = :dexterity " : UPDATE_HERO_QUERY + "";
        UPDATE_HERO_QUERY = (heroRequest.getIntelligence() != null && heroRequest.getIntelligence() >= 0 && heroRequest.getIntelligence() <= 10) ? UPDATE_HERO_QUERY + ", intelligence = :intelligence " : UPDATE_HERO_QUERY + "";
        UPDATE_HERO_QUERY += " WHERE power_stats.id = :powerStatsId ";
        return UPDATE_HERO_QUERY;

    }

    private Map<String, Object> createSqlParams(HeroRequest heroRequest) {
        Map<String, Object> params = new HashMap<>();
        if (heroRequest.getName() != null) params.put("name", heroRequest.getName());
        if (heroRequest.getRace() != null) params.put("race", heroRequest.getRace().name());
        if (heroRequest.getStrength() != null) params.put("strength", heroRequest.getStrength());
        if (heroRequest.getAgility() != null) params.put("agility", heroRequest.getAgility());
        if (heroRequest.getDexterity() != null) params.put("dexterity", heroRequest.getDexterity());
        if (heroRequest.getIntelligence() != null) params.put("intelligence", heroRequest.getIntelligence());
        return params;
    }

    public void deleteById(UUID id) {
        SqlParameterSource param = new MapSqlParameterSource("heroId", id);
        UUID powerStatsId = namedParameterJdbcTemplate.queryForObject(GET_POWER_STATS_ID_QUERY, param, UUID.class);
        final Map<String, Object> params = Map.of("hero_id", id, "powerStatsId", powerStatsId);
        namedParameterJdbcTemplate.update(DELETE_HERO_BY_ID_QUERY, params);
    }

    public UUID getHeroIdByName(String name) {
        SqlParameterSource param = new MapSqlParameterSource("heroName", name);
        return namedParameterJdbcTemplate.queryForObject(GET_HERO_ID_QUERY, param, UUID.class);
    }

    public Map<UUID, PowerStats> compareHeroes(UUID firstHeroId, UUID secondHeroId) {
        UUID powerStatsIdFirstHero = namedParameterJdbcTemplate.queryForObject(GET_POWER_STATS_ID_QUERY,
                new MapSqlParameterSource("heroId", firstHeroId), UUID.class);
        UUID powerStatsIdSecondHero = namedParameterJdbcTemplate.queryForObject(GET_POWER_STATS_ID_QUERY,
                new MapSqlParameterSource("heroId", secondHeroId), UUID.class);
        PowerStats powerStatsFirstHero = namedParameterJdbcTemplate.queryForObject(GET_POWER_STATS_QUERY,
                new MapSqlParameterSource("powerStatsId", powerStatsIdFirstHero), BeanPropertyRowMapper.newInstance(PowerStats.class));
        PowerStats powerStatsSecondHero = namedParameterJdbcTemplate.queryForObject(GET_POWER_STATS_QUERY,
                new MapSqlParameterSource("powerStatsId", powerStatsIdSecondHero), BeanPropertyRowMapper.newInstance(PowerStats.class));
        return Map.of(firstHeroId, powerStatsFirstHero, secondHeroId, powerStatsSecondHero);
    }

}
