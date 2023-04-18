package br.com.gubee.interview.web.facade;

import br.com.gubee.interview.domain.model.powerstats.PowerStats;
import br.com.gubee.interview.domain.api.powerstats.CreatePowerStats;
import br.com.gubee.interview.domain.api.powerstats.DeletePowerStats;
import br.com.gubee.interview.domain.api.powerstats.FindPowerStats;
import br.com.gubee.interview.domain.api.powerstats.UpdatePowerStats;
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
