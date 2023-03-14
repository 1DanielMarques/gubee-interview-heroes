package br.com.gubee.interview.core.features.usecase;

import br.com.gubee.interview.core.features.powerstats.PowerStatsRepository;
import br.com.gubee.interview.core.features.powerstats.PowerStatsRepositoryImpl;
import br.com.gubee.interview.core.features.usecase.interfaces.CreatePowerStats;
import br.com.gubee.interview.model.PowerStats;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class CreatePowerStatsUseCase implements CreatePowerStats {

    private final PowerStatsRepository powerStatsRepository;

    @Override
    public UUID create(PowerStats powerStats) {
        return powerStatsRepository.create(powerStats);
    }
}
