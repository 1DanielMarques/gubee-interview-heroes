package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.features.powerstats.PowerStatsService;
import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.request.CreateHeroRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HeroService {

    private final HeroRepository heroRepository;
    private final PowerStatsService powerStatsService;
    private final Assembler assembler;

    @Transactional
    public UUID create(CreateHeroRequest createHeroRequest) {
        final UUID powerStatsId = powerStatsService.create(assembler.fromRequestToPowerStats(createHeroRequest));
        return heroRepository.create(new Hero(createHeroRequest, powerStatsId));
    }
}
