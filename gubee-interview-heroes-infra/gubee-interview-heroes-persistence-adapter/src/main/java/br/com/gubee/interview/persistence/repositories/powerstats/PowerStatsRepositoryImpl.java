package br.com.gubee.interview.persistence.repositories.powerstats;

import br.com.gubee.interview.domain.exceptions.FailedCreatePowerStatsException;
import br.com.gubee.interview.domain.model.powerstats.PowerStats;
import br.com.gubee.interview.domain.repository.PowerStatsRepository;
import br.com.gubee.interview.persistence.entities.PowerStatsEntity;
import br.com.gubee.interview.domain.exceptions.PowerStatsByIdNotFoundException;
import br.com.gubee.interview.domain.exceptions.ResourceNotFoundException;
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
            throw new FailedCreatePowerStatsException();
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
    public PowerStats updatePowerStats(PowerStats powerStats) {

        try{
            return postgresRepository.updatePowerStats(PowerStatsEntity.fromPowerStats(powerStats)).toPowerStats();
        } catch (ResourceNotFoundException e) {
            throw new PowerStatsByIdNotFoundException(powerStats.getId());
        }
    }
}
