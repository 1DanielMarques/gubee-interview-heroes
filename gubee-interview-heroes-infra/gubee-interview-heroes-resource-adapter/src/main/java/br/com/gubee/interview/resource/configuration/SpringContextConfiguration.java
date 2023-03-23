package br.com.gubee.interview.resource.configuration;

import br.com.gubee.interview.domain.repository.HeroRepository;
import br.com.gubee.interview.domain.repository.PowerStatsRepository;
import br.com.gubee.interview.domain.usecase.hero.*;
import br.com.gubee.interview.domain.usecase.hero.interfaces.*;
import br.com.gubee.interview.domain.usecase.powerstats.CreatePowerStatsUseCase;
import br.com.gubee.interview.domain.usecase.powerstats.DeletePowerStatsUseCase;
import br.com.gubee.interview.domain.usecase.powerstats.FindPowerStatsUseCase;
import br.com.gubee.interview.domain.usecase.powerstats.UpdatePowerStatsUseCase;
import br.com.gubee.interview.domain.usecase.powerstats.interfaces.CreatePowerStats;
import br.com.gubee.interview.domain.usecase.powerstats.interfaces.DeletePowerStats;
import br.com.gubee.interview.domain.usecase.powerstats.interfaces.FindPowerStats;
import br.com.gubee.interview.domain.usecase.powerstats.interfaces.UpdatePowerStats;
import br.com.gubee.interview.persistence.configuration.PersistenceContextConfiguration;
import br.com.gubee.interview.resource.facade.PowerStatsFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PersistenceContextConfiguration.class)
@RequiredArgsConstructor
public class SpringContextConfiguration {


    @Bean
    public CreateHero createHero(HeroRepository heroRepository) {
        return new CreateHeroUseCase(heroRepository);
    }

    @Bean
    public FindHero findHero(HeroRepository heroRepository) {
        return new FindHeroUseCase(heroRepository);
    }

    @Bean
    public UpdateHero updateHero(HeroRepository heroRepository) {
        return new UpdateHeroUseCase(heroRepository);
    }

    @Bean
    public DeleteHero deleteHero(HeroRepository heroRepository) {
        return new DeleteHeroUseCase(heroRepository);
    }

    @Bean
    public CompareHeroes compareHeroes(HeroRepository heroRepository, PowerStatsRepository powerStatsRepository) {
        return new CompareHeroesUseCase(heroRepository, powerStatsRepository);
    }


    @Bean
    public PowerStatsFacade powerStatsFacade(PowerStatsRepository powerStatsRepository) {
        return new PowerStatsFacade(createPowerStats(powerStatsRepository),
                findPowerStats(powerStatsRepository),
                deletePowerStats(powerStatsRepository),
                updatePowerStats(powerStatsRepository));
    }

    @Bean
    public CreatePowerStats createPowerStats(PowerStatsRepository powerStatsRepository) {
        return new CreatePowerStatsUseCase(powerStatsRepository);
    }

    @Bean
    public FindPowerStats findPowerStats(PowerStatsRepository powerStatsRepository) {
        return new FindPowerStatsUseCase(powerStatsRepository);
    }

    @Bean
    public DeletePowerStats deletePowerStats(PowerStatsRepository powerStatsRepository) {
        return new DeletePowerStatsUseCase(powerStatsRepository);
    }

    @Bean
    public UpdatePowerStats updatePowerStats(PowerStatsRepository powerStatsRepository) {
        return new UpdatePowerStatsUseCase(powerStatsRepository);
    }

}
