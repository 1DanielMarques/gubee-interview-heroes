package br.com.gubee.interview.core.features.usecase.powerstats;

import br.com.gubee.interview.core.features.powerstats.PowerStatsRepository;
import br.com.gubee.interview.core.features.usecase.powerstats.interfaces.CreatePowerStats;
import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.model.entities.PowerStatsEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreatePowerStatsUseCase implements CreatePowerStats {

    private final PowerStatsRepository powerStatsRepository;

    @Override
    public PowerStats create(PowerStats powerStats) {
        return powerStatsRepository.create(PowerStatsEntity.fromPowerStats(powerStats)).toPowerStats();
    }
}