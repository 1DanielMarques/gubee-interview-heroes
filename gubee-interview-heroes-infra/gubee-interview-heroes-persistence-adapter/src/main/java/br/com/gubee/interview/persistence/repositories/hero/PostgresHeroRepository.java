package br.com.gubee.interview.persistence.repositories.hero;


import br.com.gubee.interview.persistence.entities.HeroEntity;
import br.com.gubee.interview.domain.exceptions.HeroByIdNotFoundException;
import br.com.gubee.interview.domain.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.Instant;
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

    private final String UPDATE_HERO_QUERY = " UPDATE hero SET updated_at = :updatedAt, name = :name, race = :race WHERE hero.id = :id ";


    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public HeroEntity create(HeroEntity heroEntity) throws ResourceNotFoundException {
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

    public HeroEntity updateHero(HeroEntity hero) throws ResourceNotFoundException {
        final Map<String, Object> params = Map.of("updatedAt", Timestamp.from(Instant.now()),
                "name", hero.getName(),
                "race", hero.getRace().toString(),
                "id", hero.getId());
        namedParameterJdbcTemplate.update(UPDATE_HERO_QUERY, params);
        return findById(hero.getId());
    }

    public void deleteById(UUID id) throws ResourceNotFoundException {
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
