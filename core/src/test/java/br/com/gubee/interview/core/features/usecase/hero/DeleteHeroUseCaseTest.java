package br.com.gubee.interview.core.features.usecase.hero;

import br.com.gubee.interview.core.exception.HeroByIdNotFoundException;
import br.com.gubee.interview.core.exception.HeroByNameNotFoundException;
import br.com.gubee.interview.core.exception.ResourceNotFoundException;
import br.com.gubee.interview.core.features.hero.HeroRepository;
import br.com.gubee.interview.core.features.stub.HeroRepositoryStub;
import br.com.gubee.interview.core.features.usecase.hero.interfaces.DeleteHero;
import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.enums.Race;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteHeroUseCaseTest {

    private final HeroRepository heroRepository = new HeroRepositoryStub();
    private final DeleteHero deleteHero = new DeleteHeroUseCase(heroRepository);
    private Hero createdHero;

    @BeforeEach
    void setup() {
        createdHero = heroRepository.create(Hero.builder()
                .name("BATMAN")
                .race(Race.HUMAN)
                .build());
    }

    @Test
    void shouldDeleteHeroById() {
        //when
        deleteHero.deleteById(createdHero.getId());
        //then
        assertThrows(ResourceNotFoundException.class, () -> heroRepository.findById(createdHero.getId()));
    }

    @Test
    void shouldThrowExceptionIfHeroIdNotFound() {
        //given
        var heroId = UUID.randomUUID();
        //when
        var exception = assertThrows(HeroByIdNotFoundException.class, () -> deleteHero.deleteById(heroId));
        //then
        assertEquals("Hero not found: " + heroId, exception.getMessage());
    }

    @Test
    void shouldDeleteHeroByName() {
        //when
        deleteHero.deleteByName("BATMAN");
        //then
        assertFalse(heroRepository.exist("BATMAN"));
    }

    @Test
    void shouldThrowExceptionIfHeroNameNotFound() {
        //when
        var exception = assertThrows(HeroByNameNotFoundException.class, () -> deleteHero.deleteByName("BATMA"));
        //then
        assertEquals("Hero not found: BATMA", exception.getMessage());
    }


}
