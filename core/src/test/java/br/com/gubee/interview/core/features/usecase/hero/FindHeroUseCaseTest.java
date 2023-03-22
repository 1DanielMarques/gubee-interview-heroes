package br.com.gubee.interview.core.features.usecase.hero;

import br.com.gubee.interview.core.exception.HeroByIdNotFoundException;
import br.com.gubee.interview.core.exception.HeroByNameNotFoundException;
import br.com.gubee.interview.core.features.hero.HeroRepository;
import br.com.gubee.interview.core.features.stub.HeroRepositoryStub;
import br.com.gubee.interview.core.features.usecase.hero.interfaces.FindHero;
import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.enums.Race;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class FindHeroUseCaseTest {
    private final HeroRepository heroRepository = new HeroRepositoryStub();
    private final FindHero findHero = new FindHeroUseCase(heroRepository);

    private Hero createdHero;

    @BeforeEach
    void setup() {
        createdHero = heroRepository.create(Hero.builder()
                .name("BATMAN")
                .race(Race.HUMAN)
                .build());
    }

    @Test
    void shouldReturnHeroById() {
        //when
        var heroId = findHero.findById(createdHero.getId()).getId();

        //then
        assertEquals(createdHero.getId(), heroId);
    }

    @Test
    void shouldThrowExceptionIfHeroByIdNotFound() {
        //given
        var heroId = UUID.randomUUID();
        //when
        var exception = assertThrows(HeroByIdNotFoundException.class, () -> findHero.findById(heroId));
        //then
        assertEquals("Hero not found: " + heroId, exception.getMessage());


    }

    @Test
    void shouldReturnHeroByName() {
        //when
        var heroName = findHero.findByName("BATMAN").getName();
        //then
        assertEquals(createdHero.getName(), heroName);

    }

    @Test
    void shouldThrowExceptionIfHeroByNameNotFound() {
        //when
        var exception = assertThrows(HeroByNameNotFoundException.class, () -> findHero.findByName("BATMA"));
        //then
        assertEquals("Hero not found: BATMA", exception.getMessage());
    }

    @Test
    void shouldReturnListOfHeroes() {
        //given
        var secondHero = Hero.builder()
                .name("SUPERMAN")
                .race(Race.ALIEN)
                .build();
        heroRepository.create(secondHero);
        //when
        List<Hero> heroList = findHero.findAll();
        //then
        // assertNotEquals(expectedList, heroList);
        assertTrue(heroList.containsAll(Arrays.asList(createdHero, secondHero)));
    }


}
