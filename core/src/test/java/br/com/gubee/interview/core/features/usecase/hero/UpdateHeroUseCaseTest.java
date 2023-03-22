package br.com.gubee.interview.core.features.usecase.hero;

import br.com.gubee.interview.core.exception.HeroByIdNotFoundException;
import br.com.gubee.interview.core.exception.ResourceNotFoundException;
import br.com.gubee.interview.core.features.hero.HeroRepository;
import br.com.gubee.interview.core.features.stub.HeroRepositoryStub;
import br.com.gubee.interview.core.features.usecase.hero.interfaces.UpdateHero;
import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.enums.Race;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class UpdateHeroUseCaseTest {

    private final HeroRepository heroRepository = new HeroRepositoryStub();
    private final UpdateHero updateHero = new UpdateHeroUseCase(heroRepository);

    private Hero heroCreated;

    @BeforeEach
    void setup() {
        heroCreated = heroRepository.create(Hero.builder()
                .name("BATMAN")
                .race(Race.HUMAN)
                .build());
    }

    @Test
    void shouldUpdateHeroByIdAndReturnIt() throws ResourceNotFoundException {
        //given
        var heroToUpdate = Hero.builder()
                .name("superman")
                .race(Race.ALIEN)
                .build();
        //when
        updateHero.updateById(heroCreated.getId(), heroToUpdate);
        //then
        var updatedHero = heroRepository.findById(heroCreated.getId());
        assertNotEquals(heroCreated, updatedHero);
    }

    @Test
    void shouldThrowExceptionIfHeroByIdNotFound() {
        //given
        var heroToUpdate = Hero.builder()
                .name("superman")
                .race(Race.ALIEN)
                .build();
        //when
        var heroId = UUID.randomUUID();
        var exception = assertThrows(HeroByIdNotFoundException.class, () -> updateHero.updateById(heroId, heroToUpdate));
        //then
        assertEquals("Hero not found: " + heroId, exception.getMessage());

    }


}
