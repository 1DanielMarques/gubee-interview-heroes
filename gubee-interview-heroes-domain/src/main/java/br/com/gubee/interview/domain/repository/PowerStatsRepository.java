package br.com.gubee.interview.domain.repository;

import br.com.gubee.interview.domain.exceptions.ResourceNotFoundException;
import br.com.gubee.interview.domain.model.powerstats.PowerStats;

import java.util.List;
import java.util.UUID;

public interface PowerStatsRepository {
    PowerStats create(PowerStats powerStats);

    PowerStats findById(UUID id) throws ResourceNotFoundException;

    List<PowerStats> findAll();

    void deleteById(UUID id) throws ResourceNotFoundException;

    PowerStats updatePowerStats(PowerStats powerStatsToUpdate) throws ResourceNotFoundException;
}
