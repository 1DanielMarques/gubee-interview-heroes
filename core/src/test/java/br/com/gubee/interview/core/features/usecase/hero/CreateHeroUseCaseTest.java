package br.com.gubee.interview.core.features.usecase.hero;

import br.com.gubee.interview.core.exception.HeroAlreadyExistException;
import br.com.gubee.interview.core.exception.ResourceNotFoundException;
import br.com.gubee.interview.core.features.hero.HeroRepository;
import br.com.gubee.interview.core.features.stub.HeroRepositoryStub;
import br.com.gubee.interview.core.features.usecase.hero.interfaces.CreateHero;
import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.enums.Race;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateHeroUseCaseTest {

    private final HeroRepository heroRepository = new HeroRepositoryStub();
    private final CreateHero createHero = new CreateHeroUseCase(heroRepository);

    @Test
    void shouldCreateHeroAndReturnIt() throws ResourceNotFoundException {
        //given
        var hero = Hero.builder()
                .name("Batman")
                .race(Race.HUMAN)
                .powerStatsId(UUID.randomUUID())
                .build();
        //when
        var heroSaved = createHero.create(hero);
        //then
        var heroFound = heroRepository.findById(heroSaved.getId());
        assertEquals(heroFound, heroSaved);
    }

    @Test
    void shouldThrowExceptionIfHeroAlreadyExist() {
        //given
        heroRepository.create(Hero.builder()
                .name("BATMAN")
                .race(Race.HUMAN)
                .build());
        //when
        var exception = assertThrows(HeroAlreadyExistException.class, () -> createHero.create(Hero.builder()
                .name("BATMAN")
                .race(Race.ALIEN)
                .build()));
        //then
        assertEquals("Hero BATMAN already exist", exception.getMessage());
    }


}
