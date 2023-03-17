package br.com.gubee.interview.core.features.powerstats;

import br.com.gubee.interview.core.exception.ResourceNotFoundException;
import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.model.entities.PowerStatsEntity;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
public class PowerStatsRepositoryImpl implements PowerStatsRepository {

    private final PostgresPowerStatsRepository postgresRepository;


    @Override
    public PowerStats create(PowerStats powerStats) {
        return postgresRepository.create(PowerStatsEntity.fromPowerStats(powerStats)).toPowerStats();
    }

    @Override
    public PowerStats findById(UUID id) throws ResourceNotFoundException {
        return postgresRepository.findById(id).toPowerStats();
    }

    @Override
    public List<PowerStats> findAll() {
        return postgresRepository.findAll().stream().map(entity -> entity.toPowerStats()).toList();
    }

    @Override
    public void deleteById(UUID id) throws ResourceNotFoundException {
        postgresRepository.deleteById(id);
    }

    @Override
    public PowerStats updateById(UUID id, PowerStats powerStatsToUpdate) throws ResourceNotFoundException {
        return postgresRepository.updateById(id, PowerStatsEntity.fromPowerStats(powerStatsToUpdate)).toPowerStats();
    }
}
