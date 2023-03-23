package br.com.gubee.interview.core.features.stub;

import br.com.gubee.interview.core.exception.HeroByIdNotFoundException;
import br.com.gubee.interview.core.exception.ResourceNotFoundException;
import br.com.gubee.interview.core.features.powerstats.PowerStatsRepository;
import br.com.gubee.interview.model.PowerStats;

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
    public PowerStats updateById(UUID id, PowerStats powerStatsToUpdate) {
        if (inMemory.get(id) == null) throw new HeroByIdNotFoundException(id);
        var olderPowerStats = inMemory.get(id);
        powerStatsToUpdate.setId(olderPowerStats.getId());
        powerStatsToUpdate.setCreatedAt(olderPowerStats.getCreatedAt());
        powerStatsToUpdate.setUpdatedAt(Instant.now());
        inMemory.put(powerStatsToUpdate.getId(), powerStatsToUpdate);
        return inMemory.get(powerStatsToUpdate.getId());
    }
}
