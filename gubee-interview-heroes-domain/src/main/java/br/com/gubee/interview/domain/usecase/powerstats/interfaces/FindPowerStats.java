package br.com.gubee.interview.domain.usecase.powerstats.interfaces;
import br.com.gubee.interview.domain.powerstats.PowerStats;

import java.util.List;
import java.util.UUID;

public interface FindPowerStats {
    List<PowerStats> findAll();

    PowerStats findById(UUID id);
}
