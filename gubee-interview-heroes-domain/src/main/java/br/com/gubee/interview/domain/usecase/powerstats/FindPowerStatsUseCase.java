package br.com.gubee.interview.domain.usecase.powerstats;

import br.com.gubee.interview.domain.exceptions.PowerStatsByIdNotFoundException;
import br.com.gubee.interview.domain.exceptions.ResourceNotFoundException;
import br.com.gubee.interview.domain.model.powerstats.PowerStats;
import br.com.gubee.interview.domain.repository.PowerStatsRepository;
import br.com.gubee.interview.domain.usecase.powerstats.interfaces.FindPowerStats;
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
        }catch (ResourceNotFoundException e){
            throw new PowerStatsByIdNotFoundException(id);
        }
    }


}
