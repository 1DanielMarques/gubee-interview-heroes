package br.com.gubee.interview.core.features.usecase.powerstats;

import br.com.gubee.interview.core.features.powerstats.PowerStatsRepository;
import br.com.gubee.interview.core.features.usecase.powerstats.interfaces.DeletePowerStats;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class DeletePowerStatsUseCase implements DeletePowerStats {
    private final PowerStatsRepository powerStatsRepository;

    @Override
    public void deleteById(UUID id) {
        powerStatsRepository.deleteById(id);
    }
}
