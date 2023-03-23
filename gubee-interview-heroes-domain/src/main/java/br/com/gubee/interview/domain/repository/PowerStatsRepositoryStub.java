package br.com.gubee.interview.domain.repository;


import br.com.gubee.interview.domain.powerstats.PowerStats;

import java.time.Instant;
import java.util.*;

public class PowerStatsRepositoryStub implements PowerStatsRepository {

    final Map<UUID, PowerStats> inMemory = new HashMap<>();

    @Override
    public PowerStats create(PowerStats powerStats) {
        powerStats.setId(UUID.randomUUID());
        powerStats.setCreatedAt(Instant.now());
        powerStats.setUpdatedAt(Instant.now());
        inMemory.put(powerStats.getId(), powerStats);
        return inMemory.get(powerStats.getId());
    }

    @Override
    public PowerStats findById(UUID id) {
        return inMemory.get(id);
    }

    @Override
    public List<PowerStats> findAll() {
        return inMemory.values().stream().toList();
    }

    @Override
    public void deleteById(UUID id){
        var powerStats = findById(id);
        inMemory.remove(powerStats.getId());
    }

    @Override
    public PowerStats updatePowerStats(PowerStats powerStatsToUpdate) {
     //   if (inMemory.get(powerStatsToUpdate.getId()) == null) throw new PowerStatsByIdNotFoundException(powerStatsToUpdate.getId());
        var olderPowerStats = inMemory.get(powerStatsToUpdate.getId());
        powerStatsToUpdate.setId(olderPowerStats.getId());
        powerStatsToUpdate.setCreatedAt(olderPowerStats.getCreatedAt());
        powerStatsToUpdate.setUpdatedAt(Instant.now());
        inMemory.put(powerStatsToUpdate.getId(), powerStatsToUpdate);
        return inMemory.get(powerStatsToUpdate.getId());
    }
}
