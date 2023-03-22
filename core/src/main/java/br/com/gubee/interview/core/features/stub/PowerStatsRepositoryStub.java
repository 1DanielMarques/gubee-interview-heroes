package br.com.gubee.interview.core.features.stub;

import br.com.gubee.interview.core.exception.ResourceNotFoundException;
import br.com.gubee.interview.core.features.powerstats.PowerStatsRepository;
import br.com.gubee.interview.model.PowerStats;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PowerStatsRepositoryStub implements PowerStatsRepository {

    final Map<UUID, PowerStats> inMemory = new HashMap<>();

    @Override
    public PowerStats create(PowerStats powerStats) {
        powerStats.setId(UUID.randomUUID());
        inMemory.put(powerStats.getId(), powerStats);
        return inMemory.get(powerStats.getId());
    }

    @Override
    public PowerStats findById(UUID id) throws ResourceNotFoundException {
        var powerStats = inMemory.get(id);
        if (powerStats == null) throw new ResourceNotFoundException();
        return powerStats;
    }

    @Override
    public List<PowerStats> findAll() {
        return inMemory.values().stream().toList();
    }

    @Override
    public void deleteById(UUID id) throws ResourceNotFoundException {

    }

    @Override
    public PowerStats updateById(UUID id, PowerStats powerStatsToUpdate) throws ResourceNotFoundException {
        return null;
    }
}
