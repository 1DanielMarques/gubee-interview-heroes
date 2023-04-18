package br.com.gubee.interview.domain.api.powerstats;
import br.com.gubee.interview.domain.model.powerstats.PowerStats;

import java.util.List;
import java.util.UUID;

public interface FindPowerStats {
    List<PowerStats> findAll();

    PowerStats findById(UUID id);
}
