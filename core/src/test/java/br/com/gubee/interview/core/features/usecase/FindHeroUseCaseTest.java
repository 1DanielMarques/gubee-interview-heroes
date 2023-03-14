package br.com.gubee.interview.core.features.usecase;

import br.com.gubee.interview.core.features.hero.HeroRepository;
import br.com.gubee.interview.core.features.hero.HeroRepositoryStub;
import br.com.gubee.interview.core.features.usecase.interfaces.FindHero;
import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.enums.Race;
import br.com.gubee.interview.model.request.HeroRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindHeroUseCaseTest {

    private final HeroRepository heroRepositoryStub = new HeroRepositoryStub();
    private final FindHero findHero = new FindHeroUseCase(heroRepositoryStub);
    private HeroRequest heroRequest;

    @BeforeEach
    void setup() {
        heroRequest = heroRepositoryStub.create(Hero.builder().name("Batman").race(Race.HUMAN).powerStatsId(UUID.randomUUID()).build());
    }

    @Test
    void shouldFindHeroWithItsAttributesById() {
        assertEquals(heroRequest, findHero.findById(heroRequest.getId()));
    }


}
