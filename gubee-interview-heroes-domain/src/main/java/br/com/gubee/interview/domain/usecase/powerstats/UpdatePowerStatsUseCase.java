package br.com.gubee.interview.domain.usecase.powerstats;

import br.com.gubee.interview.domain.model.powerstats.PowerStats;
import br.com.gubee.interview.domain.spi.powerstats.PowerStatsRepository;
import br.com.gubee.interview.domain.api.powerstats.UpdatePowerStats;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdatePowerStatsUseCase implements UpdatePowerStats {
    private final PowerStatsRepository powerStatsRepository;

    @Override
    public PowerStats updatePowerStats(PowerStats powerStatsToUpdate) {
            return powerStatsRepository.updatePowerStats(powerStatsToUpdate);
    }
}
