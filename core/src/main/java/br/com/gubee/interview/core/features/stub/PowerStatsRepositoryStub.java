package br.com.gubee.interview.core.features.stub;

import br.com.gubee.interview.core.exception.ResourceNotFoundException;
import br.com.gubee.interview.core.features.powerstats.PowerStatsRepository;
import br.com.gubee.interview.model.PowerStats;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PowerStatsRepositoryStub implements PowerStatsRepository {

    final Map<Object, PowerStats> inMemory = new HashMap<>();

    @Override
    public PowerStats create(PowerStats powerStats) {
        powerStats.setId(UUID.randomUUID());
        return inMemory.put(powerStats.getId(), powerStats);
    }

    @Override
    public PowerStats findById(UUID id) throws ResourceNotFoundException {
        return inMemory.get(id);
    }

    @Override
    public List<PowerStats> findAll() {
        return null;
    }

    @Override
    public void deleteById(UUID id) throws ResourceNotFoundException {

    }

    @Override
    public PowerStats updateById(UUID id, PowerStats powerStatsToUpdate) throws ResourceNotFoundException {
        return null;
    }
}
