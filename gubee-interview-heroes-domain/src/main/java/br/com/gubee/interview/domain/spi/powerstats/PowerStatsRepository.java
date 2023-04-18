package br.com.gubee.interview.domain.spi.powerstats;

import br.com.gubee.interview.domain.model.powerstats.PowerStats;

import java.util.List;
import java.util.UUID;

public interface PowerStatsRepository {
    PowerStats create(PowerStats powerStats);

    PowerStats findById(UUID id);

    List<PowerStats> findAll();

    void deleteById(UUID id);

    PowerStats updatePowerStats(PowerStats powerStatsToUpdate);
}
