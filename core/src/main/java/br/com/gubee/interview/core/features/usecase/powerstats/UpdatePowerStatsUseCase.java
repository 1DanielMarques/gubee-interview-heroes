package br.com.gubee.interview.core.features.usecase.powerstats;

import br.com.gubee.interview.core.exception.PowerStatsByIdNotFoundException;
import br.com.gubee.interview.core.exception.ResourceNotFoundException;
import br.com.gubee.interview.core.features.powerstats.PowerStatsRepository;
import br.com.gubee.interview.core.features.usecase.powerstats.interfaces.UpdatePowerStats;
import br.com.gubee.interview.model.PowerStats;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
public class UpdatePowerStatsUseCase implements UpdatePowerStats {
    private final PowerStatsRepository powerStatsRepository;

    @Transactional
    @Override
    public PowerStats updatePowerStats(PowerStats powerStatsToUpdate) {
            return powerStatsRepository.updatePowerStats(powerStatsToUpdate);
    }
}
