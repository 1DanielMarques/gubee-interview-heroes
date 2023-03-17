package br.com.gubee.interview.core.features.usecase.powerstats;

import br.com.gubee.interview.core.exception.PowerStatsByIdNotFoundException;
import br.com.gubee.interview.core.exception.ResourceNotFoundException;
import br.com.gubee.interview.core.features.powerstats.PowerStatsRepository;
import br.com.gubee.interview.core.features.usecase.powerstats.interfaces.FindPowerStats;
import br.com.gubee.interview.model.PowerStats;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class FindPowerStatsUseCase implements FindPowerStats {

    private final PowerStatsRepository powerStatsRepository;

    @Override
    public List<PowerStats> findAll() {
        return powerStatsRepository.findAll();
    }

    @Override
    public PowerStats findById(UUID id) {
        try {
            return powerStatsRepository.findById(id);
        } catch (ResourceNotFoundException e) {
            throw new PowerStatsByIdNotFoundException(id);
        }
    }


}
