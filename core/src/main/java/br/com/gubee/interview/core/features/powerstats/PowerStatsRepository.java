package br.com.gubee.interview.core.features.powerstats;

import br.com.gubee.interview.core.exception.ResourceNotFoundException;
import br.com.gubee.interview.model.PowerStats;

import java.util.List;
import java.util.UUID;

public interface PowerStatsRepository {
    PowerStats create(PowerStats powerStats);

    PowerStats findById(UUID id) throws ResourceNotFoundException;

    List<PowerStats> findAll();

    void deleteById(UUID id) throws ResourceNotFoundException;

    PowerStats updateById(UUID id, PowerStats powerStatsToUpdate) throws ResourceNotFoundException;
}
