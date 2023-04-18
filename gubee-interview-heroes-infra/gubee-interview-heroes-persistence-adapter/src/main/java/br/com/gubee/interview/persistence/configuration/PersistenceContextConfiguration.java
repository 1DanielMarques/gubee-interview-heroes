package br.com.gubee.interview.persistence.configuration;


import br.com.gubee.interview.domain.spi.hero.HeroRepository;
import br.com.gubee.interview.domain.spi.powerstats.PowerStatsRepository;
import br.com.gubee.interview.persistence.repositories.hero.HeroRepositoryImpl;
import br.com.gubee.interview.persistence.repositories.hero.PostgresHeroRepository;
import br.com.gubee.interview.persistence.repositories.powerstats.PostgresPowerStatsRepository;
import br.com.gubee.interview.persistence.repositories.powerstats.PowerStatsRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
@EnableJdbcRepositories(basePackageClasses = {PostgresHeroRepository.class, PostgresPowerStatsRepository.class})
public class PersistenceContextConfiguration {

    @Bean
    public PostgresHeroRepository postgresHeroRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        return new PostgresHeroRepository(namedParameterJdbcTemplate);
    }

    @Bean
    public PostgresPowerStatsRepository postgresPowerStatsRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        return new PostgresPowerStatsRepository(namedParameterJdbcTemplate);
    }

    @Bean
    public HeroRepository heroRepository(PostgresHeroRepository postgresHeroRepository) {
        return new HeroRepositoryImpl(postgresHeroRepository);
    }

    @Bean
    public PowerStatsRepository powerStatsRepository(PostgresPowerStatsRepository postgresPowerStatsRepository) {
        return new PowerStatsRepositoryImpl(postgresPowerStatsRepository);
    }
}
