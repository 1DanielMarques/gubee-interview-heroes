package br.com.gubee.interview.domain.usecase.powerstats;

import br.com.gubee.interview.domain.model.powerstats.PowerStats;
import br.com.gubee.interview.domain.spi.powerstats.PowerStatsRepository;
import br.com.gubee.interview.domain.api.powerstats.CreatePowerStats;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreatePowerStatsUseCase implements CreatePowerStats {

    private final PowerStatsRepository powerStatsRepository;

    @Override
    public PowerStats create(PowerStats powerStats) {
        return powerStatsRepository.create(powerStats);
    }
}
