package br.com.gubee.interview.core.configuration;

import br.com.gubee.interview.core.features.hero.HeroRepository;
import br.com.gubee.interview.core.features.hero.resource.facade.PowerStatsFacade;
import br.com.gubee.interview.core.features.powerstats.PowerStatsRepository;
import br.com.gubee.interview.core.features.usecase.hero.*;
import br.com.gubee.interview.core.features.usecase.hero.interfaces.*;
import br.com.gubee.interview.core.features.usecase.powerstats.CreatePowerStatsUseCase;
import br.com.gubee.interview.core.features.usecase.powerstats.FindPowerStatsUseCase;
import br.com.gubee.interview.core.features.usecase.powerstats.interfaces.CreatePowerStats;
import br.com.gubee.interview.core.features.usecase.powerstats.interfaces.FindPowerStats;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SpringContextConfiguration {


    private final HeroRepository heroRepository;
    private final PowerStatsRepository powerStatsRepository;

    @Bean
    public CreateHero createHero() {
        return new CreateHeroUseCase(heroRepository);
    }

    @Bean
    public FindHero findHero() {
        return new FindHeroUseCase(heroRepository);
    }

    @Bean
    public UpdateHero updateHero() {
        return new UpdateHeroUseCase(heroRepository);
    }

    @Bean
    public DeleteHero deleteHero() {
        return new DeleteHeroUseCase(heroRepository);
    }

    @Bean
    public CompareHeroes compareHeroes() {
        return new CompareHeroesUseCase(heroRepository);
    }


    @Bean
    public PowerStatsFacade powerStatsFacade() {
        return new PowerStatsFacade(createPowerStats(), findPowerStats());
    }

    @Bean
    public CreatePowerStats createPowerStats() {
        return new CreatePowerStatsUseCase(powerStatsRepository);
    }

    @Bean
    public FindPowerStats findPowerStats() {
        return new FindPowerStatsUseCase(powerStatsRepository);
    }

}
