package br.com.gubee.interview.core.features.usecase.interfaces;

import br.com.gubee.interview.model.PowerStats;

import java.util.UUID;

public interface CreatePowerStats {

    UUID create(PowerStats powerStats);
}
