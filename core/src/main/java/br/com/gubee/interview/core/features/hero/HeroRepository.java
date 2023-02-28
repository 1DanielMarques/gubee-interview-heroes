package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.request.FindHeroRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class HeroRepository {

    private static final String DELETE_HERO_QUERY = "DELETE FROM hero WHERE id = :id";

    private static final String CREATE_HERO_QUERY = "INSERT INTO hero" +
            " (name, race, power_stats_id)" +
            " VALUES (:name, :race, :powerStatsId) RETURNING id";

    private static final String FIND_HERO_ID_QUERY = "SELECT " +
            "  hero.name, hero.race, " +
            "  power_stats.strength, power_stats.agility, power_stats.dexterity, " +
            "  power_stats.intelligence " +
            "   FROM interview_service.hero INNER JOIN interview_service.power_stats ON hero.power_stats_id = power_stats.id " +
            "   WHERE hero.id = :id";

    private static final String FIND_HERO_QUERY = "SELECT * FROM hero";

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

    UUID cleaningDBAndCreating(Hero hero) {
        for (Hero h : findAll()) {
            SqlParameterSource namedParameters = new MapSqlParameterSource("id", h.getId());
            namedParameterJdbcTemplate.update(DELETE_HERO_QUERY, namedParameters);
        }
        final Map<String, Object> params = Map.of("name", hero.getName(),
                "race", hero.getRace().name(),
                "powerStatsId", hero.getPowerStatsId());

        return namedParameterJdbcTemplate.queryForObject(
                CREATE_HERO_QUERY,
                params,
                UUID.class);
    }

    public List<Hero> findAll() {
        List<Hero> heroList = namedParameterJdbcTemplate.query(FIND_HERO_QUERY, new
                BeanPropertyRowMapper<>(Hero.class));
        return heroList;
    }

    public FindHeroRequest findById(UUID id) {
        SqlParameterSource param = new MapSqlParameterSource("id", id);
        return namedParameterJdbcTemplate.queryForObject(FIND_HERO_ID_QUERY, param, BeanPropertyRowMapper.newInstance(FindHeroRequest.class));
    }
}
