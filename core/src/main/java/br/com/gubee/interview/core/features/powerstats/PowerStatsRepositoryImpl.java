package br.com.gubee.interview.core.features.powerstats;

import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.model.entities.PowerStatsEntity;
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
public class PowerStatsRepositoryImpl implements PowerStatsRepository {

    private final String CREATE_POWER_STATS_QUERY = "INSERT INTO " +
            " power_stats (strength, agility, dexterity, intelligence) VALUES (:strength, :agility, :dexterity, :intelligence) " +
            " RETURNING id ";

    private final String FIND_ALL_POWER_STATS_QUERY = " SELECT * FROM power_stats ";

    private final String FIND_BY_ID = "SELECT * FROM power_stats WHERE power_stats.id = :powerStatsId ";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public PowerStatsEntity create(PowerStatsEntity powerStatsEntity) {
        final Map<String, Object> params = Map.of("strength", powerStatsEntity.getStrength(),
                "agility", powerStatsEntity.getAgility(),
                "dexterity", powerStatsEntity.getDexterity(),
                "intelligence", powerStatsEntity.getIntelligence());
        var powerStatsId = namedParameterJdbcTemplate.queryForObject(
                CREATE_POWER_STATS_QUERY,
                params,
                UUID.class);
        return findById(powerStatsId);
    }

    @Override
    public List<PowerStatsEntity> findAll() {
        List<PowerStatsEntity> powerStatsList = namedParameterJdbcTemplate.query(FIND_ALL_POWER_STATS_QUERY,
                new BeanPropertyRowMapper<>(PowerStatsEntity.class));
        return powerStatsList;
    }

    @Override
    public PowerStatsEntity findById(UUID powerStatsId) {
        var param = new MapSqlParameterSource("powerStatsId", powerStatsId);
        return namedParameterJdbcTemplate.queryForObject(FIND_BY_ID, param, BeanPropertyRowMapper.newInstance(PowerStatsEntity.class));
    }


}
