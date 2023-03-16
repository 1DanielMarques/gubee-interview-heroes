package br.com.gubee.interview.core.features.usecase.powerstats.interfaces;

import br.com.gubee.interview.model.PowerStats;

import java.util.List;

public interface FindPowerStats {
    List<PowerStats> findAll();
}
