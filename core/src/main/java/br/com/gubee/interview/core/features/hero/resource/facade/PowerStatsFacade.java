package br.com.gubee.interview.core.features.hero.resource.facade;

import br.com.gubee.interview.core.features.usecase.powerstats.interfaces.CreatePowerStats;
import br.com.gubee.interview.core.features.usecase.powerstats.interfaces.DeletePowerStats;
import br.com.gubee.interview.core.features.usecase.powerstats.interfaces.FindPowerStats;
import br.com.gubee.interview.model.PowerStats;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class PowerStatsFacade {

    private final CreatePowerStats createPowerStats;
    private final FindPowerStats findPowerStats;
    private final DeletePowerStats deletePowerStats;

    public PowerStats create(PowerStats powerStats) {
        return createPowerStats.create(powerStats);
    }

    public PowerStats findById(UUID id) {
        return findPowerStats.findById(id);
    }


    public List<PowerStats> findAll() {
        return findPowerStats.findAll();
    }

    public void deleteById(UUID id) {
        deletePowerStats.deleteById(id);
    }


}
