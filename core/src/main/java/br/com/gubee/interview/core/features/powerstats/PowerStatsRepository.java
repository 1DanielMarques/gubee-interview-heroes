package br.com.gubee.interview.core.features.powerstats;

import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.model.entities.PowerStatsEntity;

import java.util.List;
import java.util.UUID;

public interface PowerStatsRepository {
    PowerStatsEntity create(PowerStatsEntity powerStatsEntity);

    PowerStatsEntity findById(UUID id);

    List<PowerStatsEntity> findAll();

    void deleteById(UUID id);

    PowerStatsEntity updateById(UUID id, PowerStatsEntity powerStatsToUpdate);
}
