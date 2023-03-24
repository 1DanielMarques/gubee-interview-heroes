package br.com.gubee.interview.persistence.entities;

import br.com.gubee.interview.domain.enums.Race;
import br.com.gubee.interview.domain.model.hero.Hero;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class HeroEntityTest {

    @Test
    void shouldReturnEntityFromDomain() {
        //given
        var hero = Hero.builder()
                .name("Batman")
                .race(Race.HUMAN)
                .powerStatsId(UUID.randomUUID())
                .build();
        //when
        var heroEntity = HeroEntity.fromHero(hero);
        //then
        assertThat(heroEntity).usingRecursiveComparison().isEqualTo(hero);
    }

    @Test
    void shouldReturnDomainFromEntity() {
        //given
        var heroEntity = new HeroEntity(null, "Batman", Race.HUMAN, UUID.randomUUID(), Instant.now(), Instant.now(), true);
        //when
        var hero = heroEntity.toHero();
        //then
        assertThat(hero).usingRecursiveComparison().isEqualTo(heroEntity);

    }


}
