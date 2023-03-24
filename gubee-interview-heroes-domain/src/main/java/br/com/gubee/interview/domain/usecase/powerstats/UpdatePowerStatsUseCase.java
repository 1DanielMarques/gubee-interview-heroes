package br.com.gubee.interview.domain.usecase.powerstats;

import br.com.gubee.interview.domain.exceptions.PowerStatsByIdNotFoundException;
import br.com.gubee.interview.domain.exceptions.ResourceNotFoundException;
import br.com.gubee.interview.domain.model.powerstats.PowerStats;
import br.com.gubee.interview.domain.repository.PowerStatsRepository;
import br.com.gubee.interview.domain.usecase.powerstats.interfaces.UpdatePowerStats;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdatePowerStatsUseCase implements UpdatePowerStats {
    private final PowerStatsRepository powerStatsRepository;

    @Override
    public PowerStats updatePowerStats(PowerStats powerStatsToUpdate) {
        try {
            return powerStatsRepository.updatePowerStats(powerStatsToUpdate);
        }catch (ResourceNotFoundException e){
            throw new PowerStatsByIdNotFoundException(powerStatsToUpdate.getId());
        }
    }
}
