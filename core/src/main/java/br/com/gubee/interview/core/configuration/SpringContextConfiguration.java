package br.com.gubee.interview.core.configuration;

import br.com.gubee.interview.core.features.hero.HeroRepository;
import br.com.gubee.interview.core.features.powerstats.PowerStatsRepository;
import br.com.gubee.interview.core.features.usecase.*;
import br.com.gubee.interview.core.features.usecase.interfaces.*;
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
    public CreatePowerStats createPowerStats() {
        return new CreatePowerStatsUseCase(powerStatsRepository);
    }

}
