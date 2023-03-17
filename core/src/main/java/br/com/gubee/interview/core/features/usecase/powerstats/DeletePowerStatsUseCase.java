package br.com.gubee.interview.core.features.usecase.powerstats;

import br.com.gubee.interview.core.exception.PowerStatsByIdNotFoundException;
import br.com.gubee.interview.core.exception.ResourceNotFoundException;
import br.com.gubee.interview.core.features.powerstats.PowerStatsRepository;
import br.com.gubee.interview.core.features.usecase.powerstats.interfaces.DeletePowerStats;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
public class DeletePowerStatsUseCase implements DeletePowerStats {
    private final PowerStatsRepository powerStatsRepository;

    @Transactional
    @Override
    public void deleteById(UUID id) {
        try {
            powerStatsRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new PowerStatsByIdNotFoundException(id);
        }
    }
}
