package br.com.gubee.interview.core.features.usecase.powerstats;

import br.com.gubee.interview.core.features.powerstats.PowerStatsRepository;
import br.com.gubee.interview.core.features.usecase.powerstats.interfaces.UpdatePowerStats;
import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.model.entities.PowerStatsEntity;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class UpdatePowerStatsUseCase implements UpdatePowerStats {
    private final PowerStatsRepository powerStatsRepository;

    @Override
    public PowerStats updateById(UUID id, PowerStats powerStatsToUpdate) {
        return powerStatsRepository.updateById(id, PowerStatsEntity.fromPowerStats(powerStatsToUpdate)).toPowerStats();
    }
}
