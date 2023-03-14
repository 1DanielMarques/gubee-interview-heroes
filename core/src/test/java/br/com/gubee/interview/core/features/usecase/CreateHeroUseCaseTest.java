package br.com.gubee.interview.core.features.usecase;

import br.com.gubee.interview.core.features.hero.HeroRepository;
import br.com.gubee.interview.core.features.hero.HeroRepositoryStub;
import br.com.gubee.interview.core.features.usecase.interfaces.CreateHero;
import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.enums.Race;
import br.com.gubee.interview.model.request.HeroRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class CreateHeroUseCaseTest {

    private final HeroRepository heroRepositoryStub = new HeroRepositoryStub();
    private final CreateHero createHero = new CreateHeroUseCase(heroRepositoryStub);

    @Test
    void shouldCreateHeroAndReturnIt() {
        HeroRequest heroRequest = createHeroRequest();
        HeroRequest createdHero = heroRepositoryStub.create(new Hero(heroRequest, UUID.randomUUID()));
        heroRequest.setId(createdHero.getId());
        Assertions.assertEquals(heroRequest, createdHero);
    }

    private HeroRequest createHeroRequest() {
        return HeroRequest.builder()
                .name("Batman")
                .agility(5)
                .dexterity(8)
                .strength(6)
                .intelligence(10)
                .race(Race.HUMAN)
                .build();
    }


}
