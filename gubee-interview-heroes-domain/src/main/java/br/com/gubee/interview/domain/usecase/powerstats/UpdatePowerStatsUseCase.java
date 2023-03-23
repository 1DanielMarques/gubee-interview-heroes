package br.com.gubee.interview.domain.usecase.powerstats;

import br.com.gubee.interview.domain.powerstats.PowerStats;
import br.com.gubee.interview.domain.repository.PowerStatsRepository;
import br.com.gubee.interview.domain.usecase.powerstats.interfaces.UpdatePowerStats;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdatePowerStatsUseCase implements UpdatePowerStats {
    private final PowerStatsRepository powerStatsRepository;

    @Override
    public PowerStats updatePowerStats(PowerStats powerStatsToUpdate) {
            return powerStatsRepository.updatePowerStats(powerStatsToUpdate);
    }
}
