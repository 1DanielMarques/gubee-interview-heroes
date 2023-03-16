package br.com.gubee.interview.core.features.hero.resource.facade;

import br.com.gubee.interview.core.features.usecase.powerstats.interfaces.CreatePowerStats;
import br.com.gubee.interview.core.features.usecase.powerstats.interfaces.FindPowerStats;
import br.com.gubee.interview.model.PowerStats;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PowerStatsFacade {

    private final CreatePowerStats createPowerStats;
    private final FindPowerStats findPowerStats;

    public PowerStats create(PowerStats powerStats) {
        return createPowerStats.create(powerStats);
    }


    public List<PowerStats> findAll() {
        return findPowerStats.findAll();
    }


}
