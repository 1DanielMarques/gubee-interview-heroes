package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.exception.HeroByIdNotFoundException;
import br.com.gubee.interview.core.exception.ResourceNotFoundException;
import br.com.gubee.interview.model.entities.HeroEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PostgresHeroRepository {

    private final String CREATE_HERO_QUERY = " INSERT " +
            " INTO hero " +
            " (name, race, power_stats_id) " +
            " VALUES (:name, :race, :powerStatsId) RETURNING id ";

    private final String FIND_HERO_BY_ID_QUERY = " SELECT * FROM hero WHERE hero.id = :id ";

    private final String FIND_HERO_BY_NAME_QUERY = " SELECT * FROM hero WHERE hero.name = :name ";

    private final String FIND_ALL_HEROES_QUERY = " SELECT * FROM hero ";

    private final String DELETE_BY_ID_QUERY = " DELETE FROM hero WHERE hero.id = :id ";
    private final String DELETE_BY_NAME_QUERY = " DELETE FROM hero WHERE hero.name = :name ";


    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public HeroEntity create(HeroEntity heroEntity) throws ResourceNotFoundException{
        final Map<String, Object> params = Map.of("name", heroEntity.getName(),
                "race", heroEntity.getRace().name(),
                "powerStatsId", heroEntity.getPowerStatsId());
        var heroId = namedParameterJdbcTemplate.queryForObject(
                CREATE_HERO_QUERY,
                params,
                UUID.class);
        return findById(heroId);
    }


    public List<HeroEntity> findAll() {
        List<HeroEntity> heroList = namedParameterJdbcTemplate.query(FIND_ALL_HEROES_QUERY, new
                BeanPropertyRowMapper<>(HeroEntity.class));
        return heroList;
    }

    public HeroEntity findById(UUID id) throws ResourceNotFoundException {
        try {
            var param = new MapSqlParameterSource("id", id);
            return namedParameterJdbcTemplate.queryForObject(FIND_HERO_BY_ID_QUERY, param, BeanPropertyRowMapper.newInstance(HeroEntity.class));
        } catch (DataAccessException e) {
            throw new ResourceNotFoundException();
        }
    }

    public HeroEntity findByName(String name) throws ResourceNotFoundException {
        try {
            var param = new MapSqlParameterSource("name", name);
            return namedParameterJdbcTemplate.queryForObject(FIND_HERO_BY_NAME_QUERY, param, BeanPropertyRowMapper.newInstance(HeroEntity.class));
        } catch (DataAccessException e) {
            throw new ResourceNotFoundException();
        }

    }


    public HeroEntity updateById(UUID id, HeroEntity hero) throws ResourceNotFoundException {
        final Map<String, Object> params = createSqlParams(hero);
        params.put("id", id);
        namedParameterJdbcTemplate.update(createUpdateQuery(hero), params);
        return findById(id);
        // trocar toda a logica por um SAVE
    }

    private String createUpdateQuery(HeroEntity hero) {
        var UPDATE_HERO_QUERY = " UPDATE " +
                " hero " +
                " SET ";
        UPDATE_HERO_QUERY += " updated_at = :updatedAt ";
        UPDATE_HERO_QUERY = (hero.getName() != null) ? UPDATE_HERO_QUERY + ", name = :name " : UPDATE_HERO_QUERY + "";
        UPDATE_HERO_QUERY = (hero.getRace() != null) ? UPDATE_HERO_QUERY + ", race = :race " : UPDATE_HERO_QUERY + "";
        UPDATE_HERO_QUERY += " WHERE hero.id = :id ";
        return UPDATE_HERO_QUERY;

    }

    private Map<String, Object> createSqlParams(HeroEntity hero) {
        Map<String, Object> params = new HashMap<>();
        if (hero.getName() != null) params.put("name", hero.getName());
        if (hero.getRace() != null) params.put("race", hero.getRace().name());
        params.put("updatedAt", Timestamp.from(Instant.now()));
        return params;
    }

    public void deleteById(UUID id) throws ResourceNotFoundException{
        try {
        var param = new MapSqlParameterSource("id", id);
        var rowsAffected = namedParameterJdbcTemplate.update(DELETE_BY_ID_QUERY, param);
        if (rowsAffected == 0) throw new HeroByIdNotFoundException(id);
        } catch (DataAccessException e) {
            throw new ResourceNotFoundException();
        }
    }

    public void deleteByName(String name) throws ResourceNotFoundException {
        try {
            var param = new MapSqlParameterSource("name", name);
            namedParameterJdbcTemplate.update(DELETE_BY_NAME_QUERY, param);
        } catch (DataAccessException e) {
            throw new ResourceNotFoundException();
        }

    }

    public boolean exist(String name) {
        final String HERO_ALREADY_EXIST_QUERY = " SELECT COUNT(hero.name) FROM hero WHERE hero.name = :name ";
        var param = new MapSqlParameterSource("name", name);
        var exist = namedParameterJdbcTemplate.queryForObject(HERO_ALREADY_EXIST_QUERY, param, Integer.class);
        return exist != null && exist > 0;
    }


}
