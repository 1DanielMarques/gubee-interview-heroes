package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.exception.HeroByIdNotFoundException;
import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.model.dto.HeroDTO;
import br.com.gubee.interview.model.entities.HeroEntity;
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
public class HeroRepositoryImpl implements HeroRepository {

    private final String CREATE_HERO_QUERY = " INSERT " +
            " INTO hero " +
            " (name, race, power_stats_id) " +
            " VALUES (:name, :race, :powerStatsId) RETURNING id ";

    private final String FIND_HERO_BY_ID_QUERY = " SELECT * FROM hero WHERE hero.id = :id ";

    private final String FIND_HERO_BY_NAME_QUERY = " SELECT " +
            "  hero.name, hero.race, " +
            "  power_stats.strength, power_stats.agility, power_stats.dexterity, " +
            "  power_stats.intelligence " +
            "  FROM hero INNER JOIN power_stats ON hero.power_stats_id = power_stats.id " +
            "  WHERE hero.name = :name ";

    private final String FIND_ALL_HEROES_QUERY = "SELECT " +
            "  * FROM HERO ";

    private final String DELETE_HERO_BY_ID_QUERY = " DELETE " +
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

    private final String GET_POWER_STATS_QUERY = " SELECT " +
            " strength, agility, dexterity, intelligence " +
            " FROM power_stats " +
            " WHERE id = :powerStatsId ";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public HeroEntity create(HeroEntity heroEntity) {
        final Map<String, Object> params = Map.of("name", heroEntity.getName(),
                "race", heroEntity.getRace().name(),
                "powerStatsId", heroEntity.getPowerStatsId());
        var heroId = namedParameterJdbcTemplate.queryForObject(
                CREATE_HERO_QUERY,
                params,
                UUID.class);
        return findById(heroId);
    }


    public List<Hero> findAll() {
        List<Hero> heroList = namedParameterJdbcTemplate.query(FIND_ALL_HEROES_QUERY, new
                BeanPropertyRowMapper<>(Hero.class));
        return heroList;
    }

    public HeroEntity findById(UUID id) {
        SqlParameterSource param = new MapSqlParameterSource("id", id);
        return namedParameterJdbcTemplate.queryForObject(FIND_HERO_BY_ID_QUERY, param, BeanPropertyRowMapper.newInstance(HeroEntity.class));
    }

    public HeroDTO findByName(String name) {
        SqlParameterSource param = new MapSqlParameterSource("name", name);
        return namedParameterJdbcTemplate.queryForObject(FIND_HERO_BY_NAME_QUERY, param, BeanPropertyRowMapper.newInstance(HeroDTO.class));
    }


    public void updateById(UUID id, HeroDTO heroDTO) {
        SqlParameterSource param = new MapSqlParameterSource("heroId", id);
        UUID powerStatsId = namedParameterJdbcTemplate.queryForObject(GET_POWER_STATS_ID_QUERY, param, UUID.class);
        final Map<String, Object> params = createSqlParams(heroDTO);
        params.put("heroId", id);
        params.put("powerStatsId", powerStatsId);
        params.put("heroUpdatedAt", Timestamp.from(Instant.now()));
        params.put("powerStatsUpdatedAt", Timestamp.from(Instant.now()));
        namedParameterJdbcTemplate.update(createUpdateQuery(heroDTO), params);
    }

    private String createUpdateQuery(HeroDTO heroDTO) {
        String UPDATE_HERO_QUERY = " UPDATE " +
                " interview_service.hero " +
                " SET ";
        UPDATE_HERO_QUERY += " updated_at = :heroUpdatedAt ";
        UPDATE_HERO_QUERY = (heroDTO.getName() != null) ? UPDATE_HERO_QUERY + ", name = :name " : UPDATE_HERO_QUERY + "";
        UPDATE_HERO_QUERY = (heroDTO.getRace() != null) ? UPDATE_HERO_QUERY + ", race = :race " : UPDATE_HERO_QUERY + "";
        UPDATE_HERO_QUERY += " WHERE hero.id = :heroId; ";
        UPDATE_HERO_QUERY += " UPDATE interview_service.power_stats SET ";
        UPDATE_HERO_QUERY += " updated_at = :powerStatsUpdatedAt  ";
        UPDATE_HERO_QUERY = (heroDTO.getStrength() != null && heroDTO.getStrength() >= 0 && heroDTO.getStrength() <= 10) ? UPDATE_HERO_QUERY + ", strength = :strength " : UPDATE_HERO_QUERY + "";
        UPDATE_HERO_QUERY = (heroDTO.getAgility() != null && heroDTO.getAgility() >= 0 && heroDTO.getAgility() <= 10) ? UPDATE_HERO_QUERY + ", agility = :agility " : UPDATE_HERO_QUERY + "";
        UPDATE_HERO_QUERY = (heroDTO.getDexterity() != null && heroDTO.getDexterity() >= 0 && heroDTO.getDexterity() <= 10) ? UPDATE_HERO_QUERY + ", dexterity = :dexterity " : UPDATE_HERO_QUERY + "";
        UPDATE_HERO_QUERY = (heroDTO.getIntelligence() != null && heroDTO.getIntelligence() >= 0 && heroDTO.getIntelligence() <= 10) ? UPDATE_HERO_QUERY + ", intelligence = :intelligence " : UPDATE_HERO_QUERY + "";
        UPDATE_HERO_QUERY += " WHERE power_stats.id = :powerStatsId ";
        return UPDATE_HERO_QUERY;

    }

    private Map<String, Object> createSqlParams(HeroDTO heroDTO) {
        Map<String, Object> params = new HashMap<>();
        if (heroDTO.getName() != null) params.put("name", heroDTO.getName());
        if (heroDTO.getRace() != null) params.put("race", heroDTO.getRace().name());
        if (heroDTO.getStrength() != null) params.put("strength", heroDTO.getStrength());
        if (heroDTO.getAgility() != null) params.put("agility", heroDTO.getAgility());
        if (heroDTO.getDexterity() != null) params.put("dexterity", heroDTO.getDexterity());
        if (heroDTO.getIntelligence() != null) params.put("intelligence", heroDTO.getIntelligence());
        return params;
    }

    public void deleteById(UUID id) {
        SqlParameterSource param = new MapSqlParameterSource("heroId", id);
        UUID powerStatsId = namedParameterJdbcTemplate.queryForObject(GET_POWER_STATS_ID_QUERY, param, UUID.class);
        final Map<String, Object> params = Map.of("heroId", id, "powerStatsId", powerStatsId);
        namedParameterJdbcTemplate.update(DELETE_HERO_BY_ID_QUERY, params);
    }


    public UUID getHeroIdByName(String name) {
        SqlParameterSource param = new MapSqlParameterSource("heroName", name);
        UUID heroId = namedParameterJdbcTemplate.queryForObject(GET_HERO_ID_QUERY, param, UUID.class);
        if (heroId != null) {
            return heroId;
        } else {
            throw new HeroByIdNotFoundException(heroId);
        }

    }

    //REFATORAR
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
