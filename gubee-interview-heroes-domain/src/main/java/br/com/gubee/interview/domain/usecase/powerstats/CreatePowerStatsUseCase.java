package br.com.gubee.interview.domain.usecase.powerstats;

import br.com.gubee.interview.domain.powerstats.PowerStats;
import br.com.gubee.interview.domain.repository.PowerStatsRepository;
import br.com.gubee.interview.domain.usecase.powerstats.interfaces.CreatePowerStats;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreatePowerStatsUseCase implements CreatePowerStats {

    private final PowerStatsRepository powerStatsRepository;

    @Override
    public PowerStats create(PowerStats powerStats) {
        return powerStatsRepository.create(powerStats);
    }
}
