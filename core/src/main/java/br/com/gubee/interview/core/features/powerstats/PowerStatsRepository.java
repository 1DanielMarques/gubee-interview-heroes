package br.com.gubee.interview.core.features.powerstats;

import br.com.gubee.interview.model.PowerStats;

import java.util.UUID;

public interface PowerStatsRepository {
    UUID create(PowerStats powerStats);
}
