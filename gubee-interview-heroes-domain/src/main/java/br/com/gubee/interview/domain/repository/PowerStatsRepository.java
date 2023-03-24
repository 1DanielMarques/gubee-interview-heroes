package br.com.gubee.interview.domain.repository;

import br.com.gubee.interview.domain.powerstats.PowerStats;

import java.util.List;
import java.util.UUID;

public interface PowerStatsRepository {
    PowerStats create(PowerStats powerStats);

    PowerStats findById(UUID id);

    List<PowerStats> findAll();

    void deleteById(UUID id);

    PowerStats updatePowerStats(PowerStats powerStatsToUpdate);
}