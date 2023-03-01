package br.com.gubee.interview.core.features.powerstats;

import br.com.gubee.interview.core.features.usecase.interfaces.CreatePowerStats;
import br.com.gubee.interview.model.PowerStats;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PowerStatsService {

    private final CreatePowerStats createPowerStats;

    @Transactional
    public UUID create(PowerStats powerStats) {
        return createPowerStats.create(powerStats);
    }
}
