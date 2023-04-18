package br.com.gubee.interview.domain.usecase.powerstats;

import br.com.gubee.interview.domain.spi.powerstats.PowerStatsRepository;
import br.com.gubee.interview.domain.api.powerstats.DeletePowerStats;
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
