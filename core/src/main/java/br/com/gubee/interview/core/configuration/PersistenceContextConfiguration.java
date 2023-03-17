package br.com.gubee.interview.core.configuration;

import br.com.gubee.interview.core.features.hero.HeroRepository;
import br.com.gubee.interview.core.features.hero.HeroRepositoryImpl;
import br.com.gubee.interview.core.features.hero.PostgresHeroRepository;
import br.com.gubee.interview.core.features.powerstats.PostgresPowerStatsRepository;
import br.com.gubee.interview.core.features.powerstats.PowerStatsRepository;
import br.com.gubee.interview.core.features.powerstats.PowerStatsRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@Configuration
@EnableJdbcRepositories(basePackageClasses = {PostgresHeroRepository.class, PostgresPowerStatsRepository.class})
public class PersistenceContextConfiguration {

    @Bean
    public HeroRepository heroRepository(PostgresHeroRepository postgresHeroRepository) {
        return new HeroRepositoryImpl(postgresHeroRepository);
    }

    @Bean
    public PowerStatsRepository powerStatsRepository(PostgresPowerStatsRepository postgresPowerStatsRepository) {
        return new PowerStatsRepositoryImpl(postgresPowerStatsRepository);
    }
}
