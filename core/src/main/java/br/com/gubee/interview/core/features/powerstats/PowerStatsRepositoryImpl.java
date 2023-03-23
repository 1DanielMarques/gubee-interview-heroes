package br.com.gubee.interview.core.features.powerstats;

import br.com.gubee.interview.core.exception.PowerStatsByIdNotFoundException;
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
        try {
            return postgresRepository.create(PowerStatsEntity.fromPowerStats(powerStats)).toPowerStats();
        } catch (ResourceNotFoundException e) {
            //throw new ErrorCreatingPowerStatsException();
            throw new PowerStatsByIdNotFoundException(powerStats.getId());
        }

    }

    @Override
    public PowerStats findById(UUID id) {
        try {
            return postgresRepository.findById(id).toPowerStats();
        } catch (ResourceNotFoundException e) {
            throw new PowerStatsByIdNotFoundException(id);
        }

    }

    @Override
    public List<PowerStats> findAll() {
        return postgresRepository.findAll().stream().map(PowerStatsEntity::toPowerStats).toList();
    }

    @Override
    public void deleteById(UUID id) {
        try {
            postgresRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new PowerStatsByIdNotFoundException(id);
        }

    }

    @Override
    public PowerStats updatePowerStats(PowerStats powerStatsToUpdate) {
        try{
        return postgresRepository.updatePowerStats(PowerStatsEntity.fromPowerStats(powerStatsToUpdate)).toPowerStats();
        } catch (ResourceNotFoundException e) {
            throw new PowerStatsByIdNotFoundException(powerStatsToUpdate.getId());
        }
    }
}
