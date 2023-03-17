package br.com.gubee.interview.core.features.powerstats;

import br.com.gubee.interview.core.exception.PowerStatsByIdNotFoundException;
import br.com.gubee.interview.core.exception.ResourceNotFoundException;
import br.com.gubee.interview.model.entities.PowerStatsEntity;
import lombok.RequiredArgsConstructor;
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
public class PowerStatsRepositoryImpl implements PowerStatsRepository {

    private final String CREATE_POWER_STATS_QUERY = "INSERT INTO " +
            " power_stats (strength, agility, dexterity, intelligence) VALUES (:strength, :agility, :dexterity, :intelligence) " +
            " RETURNING id ";

    private final String FIND_ALL_POWER_STATS_QUERY = " SELECT * FROM power_stats ";

    private final String FIND_BY_ID_QUERY = "SELECT * FROM power_stats WHERE power_stats.id = :id ";

    private final String DELETE_BY_ID_QUERY = " DELETE FROM power_stats WHERE power_stats.id = :id ";


    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public PowerStatsEntity create(PowerStatsEntity powerStatsEntity) {
        final Map<String, Object> params = Map.of("strength", powerStatsEntity.getStrength(),
                "agility", powerStatsEntity.getAgility(),
                "dexterity", powerStatsEntity.getDexterity(),
                "intelligence", powerStatsEntity.getIntelligence());
        var id = namedParameterJdbcTemplate.queryForObject(
                CREATE_POWER_STATS_QUERY,
                params,
                UUID.class);
        try {
            return findById(id);
        } catch (ResourceNotFoundException e) {
            throw new PowerStatsByIdNotFoundException(id);
        }
    }

    @Override
    public List<PowerStatsEntity> findAll() {
        List<PowerStatsEntity> powerStatsList = namedParameterJdbcTemplate.query(FIND_ALL_POWER_STATS_QUERY,
                new BeanPropertyRowMapper<>(PowerStatsEntity.class));
        return powerStatsList;
    }

    @Override
    public PowerStatsEntity findById(UUID id) throws ResourceNotFoundException {
        try {
            var param = new MapSqlParameterSource("id", id);
            return namedParameterJdbcTemplate.queryForObject(FIND_BY_ID_QUERY, param, BeanPropertyRowMapper.newInstance(PowerStatsEntity.class));
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException();
        }

    }

    @Override
    public void deleteById(UUID id) throws ResourceNotFoundException {
        var param = new MapSqlParameterSource("id", id);
        namedParameterJdbcTemplate.update(DELETE_BY_ID_QUERY, param);
    }


    @Override
    public PowerStatsEntity updateById(UUID id, PowerStatsEntity powerStatsToUpdate) throws ResourceNotFoundException {
        Map<String, Object> params = createUpdateParams(powerStatsToUpdate);
        params.put("id", id);
        namedParameterJdbcTemplate.update(createUpdateQuery(powerStatsToUpdate), params);
        return findById(id);
    }

    private String createUpdateQuery(PowerStatsEntity powerStats) {
        var UPDATE_POWER_STATS_QUERY = " UPDATE " +
                " power_stats " +
                " SET ";
        UPDATE_POWER_STATS_QUERY += " updated_at = :updatedAt ";
        UPDATE_POWER_STATS_QUERY = ((powerStats.getAgility() >= 0 || powerStats.getAgility() <= 10) && powerStats.getAgility() != null) ? UPDATE_POWER_STATS_QUERY + ", agility = :agility " : UPDATE_POWER_STATS_QUERY + "";
        UPDATE_POWER_STATS_QUERY = ((powerStats.getDexterity() >= 0 || powerStats.getDexterity() <= 10) && powerStats.getDexterity() != null) ? UPDATE_POWER_STATS_QUERY + ", dexterity = :dexterity " : UPDATE_POWER_STATS_QUERY + "";
        UPDATE_POWER_STATS_QUERY = ((powerStats.getStrength() >= 0 || powerStats.getStrength() <= 10) && powerStats.toPowerStats().getStrength() != null) ? UPDATE_POWER_STATS_QUERY + ", strength = :strength " : UPDATE_POWER_STATS_QUERY + "";
        UPDATE_POWER_STATS_QUERY = ((powerStats.getIntelligence() >= 0 || powerStats.getIntelligence() <= 10) && powerStats.getIntelligence() != null) ? UPDATE_POWER_STATS_QUERY + ", intelligence = :intelligence " : UPDATE_POWER_STATS_QUERY + "";
        UPDATE_POWER_STATS_QUERY += " WHERE power_stats.id = :id ";
        return UPDATE_POWER_STATS_QUERY;
    }

    private Map<String, Object> createUpdateParams(PowerStatsEntity powerStats) {
        Map<String, Object> params = new HashMap<>();
        if (powerStats.getAgility() != null) params.put("agility", powerStats.getAgility());
        if (powerStats.getDexterity() != null) params.put("dexterity", powerStats.getDexterity());
        if (powerStats.getStrength() != null) params.put("strength", powerStats.getStrength());
        if (powerStats.getIntelligence() != null) params.put("intelligence", powerStats.getIntelligence());
        params.put("updatedAt", Timestamp.from(Instant.now()));
        return params;
    }


}
