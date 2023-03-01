package br.com.gubee.interview.core.features.usecase;

import br.com.gubee.interview.core.features.hero.HeroRepository;
import br.com.gubee.interview.core.features.powerstats.PowerStatsService;
import br.com.gubee.interview.core.features.usecase.interfaces.CreateHero;
import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.model.request.HeroRequest;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class CreateHeroUseCase implements CreateHero {

    private final PowerStatsService powerStatsService;
    private final HeroRepository heroRepository;

    @Override
    public UUID create(HeroRequest heroRequest) {
        final UUID powerStatsId = powerStatsService.create(new PowerStats(heroRequest));
        return heroRepository.create(new Hero(heroRequest, powerStatsId));
    }
}
