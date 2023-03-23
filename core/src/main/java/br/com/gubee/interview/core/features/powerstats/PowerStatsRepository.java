package br.com.gubee.interview.core.features.powerstats;

import br.com.gubee.interview.core.exception.ResourceNotFoundException;
import br.com.gubee.interview.model.PowerStats;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PowerStatsRepository {
    PowerStats create(PowerStats powerStats);

    PowerStats findById(UUID id);

    List<PowerStats> findAll();

    void deleteById(UUID id);

    PowerStats updatePowerStats(PowerStats powerStatsToUpdate);
}
