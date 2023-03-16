package br.com.gubee.interview.core.features.usecase.powerstats;

import br.com.gubee.interview.core.features.powerstats.PowerStatsRepository;
import br.com.gubee.interview.core.features.usecase.powerstats.interfaces.FindPowerStats;
import br.com.gubee.interview.model.PowerStats;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class FindPowerStatsUseCase implements FindPowerStats {

    private final PowerStatsRepository powerStatsRepository;

    @Override
    public List<PowerStats> findAll() {
        return powerStatsRepository.findAll().stream().map(entity -> entity.toPowerStats()).toList();
    }
}
