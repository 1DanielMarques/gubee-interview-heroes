package br.com.gubee.interview.core.features.usecase.powerstats.interfaces;

import br.com.gubee.interview.model.PowerStats;

import java.util.List;
import java.util.UUID;

public interface FindPowerStats {
    List<PowerStats> findAll();

    PowerStats findById(UUID id);
}
