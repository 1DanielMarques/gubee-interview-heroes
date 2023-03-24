package br.com.gubee.interview.resource.facade;

import br.com.gubee.interview.domain.model.powerstats.PowerStats;
import br.com.gubee.interview.domain.usecase.powerstats.interfaces.CreatePowerStats;
import br.com.gubee.interview.domain.usecase.powerstats.interfaces.DeletePowerStats;
import br.com.gubee.interview.domain.usecase.powerstats.interfaces.FindPowerStats;
import br.com.gubee.interview.domain.usecase.powerstats.interfaces.UpdatePowerStats;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class PowerStatsFacade {

    private final CreatePowerStats createPowerStats;
    private final FindPowerStats findPowerStats;
    private final DeletePowerStats deletePowerStats;
    private final UpdatePowerStats updatePowerStats;

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

    public PowerStats updatePowerStats(PowerStats powerStatsToUpdate) {
        return updatePowerStats.updatePowerStats(powerStatsToUpdate);
    }


}
