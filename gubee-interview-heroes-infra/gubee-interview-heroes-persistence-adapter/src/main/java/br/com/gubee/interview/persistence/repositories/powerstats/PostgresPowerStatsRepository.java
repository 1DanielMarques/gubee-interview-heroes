package br.com.gubee.interview.persistence.repositories.powerstats;

import br.com.gubee.interview.persistence.entities.PowerStatsEntity;
import br.com.gubee.interview.persistence.exceptions.ResourceNotFoundException;
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
public class PostgresPowerStatsRepository {
    private final String CREATE_POWER_STATS_QUERY = "INSERT INTO " +
            " power_stats (strength, agility, dexterity, intelligence) VALUES (:strength, :agility, :dexterity, :intelligence) " +
            " RETURNING id ";

    private final String FIND_ALL_POWER_STATS_QUERY = " SELECT * FROM power_stats ";

    private final String FIND_BY_ID_QUERY = "SELECT * FROM power_stats WHERE power_stats.id = :id ";

    private final String DELETE_BY_ID_QUERY = " DELETE FROM power_stats WHERE power_stats.id = :id ";

    private final String UPDATE_POWER_STATS_QUERY = " UPDATE power_stats SET updated_at = :updatedAt, " +
            "  agility = :agility,  dexterity = :dexterity, strength = :strength, intelligence = :intelligence WHERE power_stats.id = :id ";


    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public PowerStatsEntity create(PowerStatsEntity powerStatsEntity) throws ResourceNotFoundException {
        final Map<String, Object> params = Map.of("strength", powerStatsEntity.getStrength(),
                "agility", powerStatsEntity.getAgility(),
                "dexterity", powerStatsEntity.getDexterity(),
                "intelligence", powerStatsEntity.getIntelligence());
        var id = namedParameterJdbcTemplate.queryForObject(
                CREATE_POWER_STATS_QUERY,
                params,
                UUID.class);
        return findById(id);
    }


    public List<PowerStatsEntity> findAll() {
        List<PowerStatsEntity> powerStatsList = namedParameterJdbcTemplate.query(FIND_ALL_POWER_STATS_QUERY,
                new BeanPropertyRowMapper<>(PowerStatsEntity.class));
        return powerStatsList;
    }


    public PowerStatsEntity findById(UUID id) throws ResourceNotFoundException {
        try {
            var param = new MapSqlParameterSource("id", id);
            return namedParameterJdbcTemplate.queryForObject(FIND_BY_ID_QUERY, param, BeanPropertyRowMapper.newInstance(PowerStatsEntity.class));
        } catch (DataAccessException e) {
            throw new ResourceNotFoundException();
        }

    }


    public void deleteById(UUID id) throws ResourceNotFoundException {
        try {
            var param = new MapSqlParameterSource("id", id);
            namedParameterJdbcTemplate.update(DELETE_BY_ID_QUERY, param);
        } catch (DataAccessException e) {
            throw new ResourceNotFoundException();
        }

    }


    public PowerStatsEntity updatePowerStats(PowerStatsEntity powerStatsToUpdate) throws ResourceNotFoundException {
        final Map<String, Object> params = Map.of("updatedAt", Timestamp.from(Instant.now()),
                "agility", powerStatsToUpdate.getAgility(),
                "dexterity", powerStatsToUpdate.getDexterity(),
                "strength", powerStatsToUpdate.getStrength(),
                "intelligence", powerStatsToUpdate.getIntelligence(),
                "id", powerStatsToUpdate.getId());
        namedParameterJdbcTemplate.update(UPDATE_POWER_STATS_QUERY, params);
        return findById(powerStatsToUpdate.getId());
    }

}
