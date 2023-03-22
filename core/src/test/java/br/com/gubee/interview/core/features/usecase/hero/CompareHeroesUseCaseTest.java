package br.com.gubee.interview.core.features.usecase.hero;

import br.com.gubee.interview.core.exception.HeroByNameNotFoundException;
import br.com.gubee.interview.core.features.hero.HeroRepository;
import br.com.gubee.interview.core.features.powerstats.PowerStatsRepository;
import br.com.gubee.interview.core.features.stub.HeroRepositoryStub;
import br.com.gubee.interview.core.features.stub.PowerStatsRepositoryStub;
import br.com.gubee.interview.core.features.usecase.hero.interfaces.CompareHeroes;
import br.com.gubee.interview.model.ComparedHeroes;
import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.model.enums.Race;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CompareHeroesUseCaseTest {

    private final HeroRepository heroRepositoryStub = new HeroRepositoryStub();
    private final PowerStatsRepository powerStatsRepositoryStub = new PowerStatsRepositoryStub();
    private final CompareHeroes compareHeroes = new CompareHeroesUseCase(heroRepositoryStub, powerStatsRepositoryStub);
    private Hero firstHero;
    private Hero secondHero;

    @BeforeEach
    void setup() {
        var firstPowerStats = PowerStats.builder()
                .agility(5)
                .dexterity(8)
                .strength(6)
                .intelligence(10)
                .build();
        powerStatsRepositoryStub.create(firstPowerStats);
        firstHero = Hero.builder()
                .name("BATMAN")
                .race(Race.HUMAN)
                .powerStatsId(firstPowerStats.getId())
                .build();
        heroRepositoryStub.create(firstHero);

        //Second Hero
        var secondPowerStats = PowerStats.builder()
                .agility(7)
                .dexterity(4)
                .strength(10)
                .intelligence(8)
                .build();
        powerStatsRepositoryStub.create(secondPowerStats);
        secondHero = Hero.builder()
                .name("SUPERMAN")
                .race(Race.ALIEN)
                .powerStatsId(secondPowerStats.getId())
                .build();
        heroRepositoryStub.create(secondHero);
    }


    @Test
    @DisplayName("Should compare two heroes attributes and return them")
    void shouldCompareHeroesAttributesAndReturnThem() {
        //Given
        //Expected result
        ComparedHeroes expected = ComparedHeroes.builder()
                .firstId(firstHero.getId())
                .firstAgility(-5)
                .firstDexterity(8)
                .firstStrength(-6)
                .firstIntelligence(10)

                .secondId(secondHero.getId())
                .secondAgility(7)
                .secondDexterity(-4)
                .secondStrength(10)
                .secondIntelligence(-8)
                .build();

        //When
        ComparedHeroes result = compareHeroes.compareHeroes("batman", "superman");
        //Then
        Assertions.assertEquals(expected, result);
    }

    @Test
    void shouldThrowExceptionIfFirstNameNotFound() {
        //when
        var exception = assertThrows(HeroByNameNotFoundException.class, () -> compareHeroes.compareHeroes("batma", "superman"));
        //then
        assertEquals("Hero not found: batma", exception.getMessage());


    }

    @Test
    void shouldThrowExceptionIfSecondNameNotFound() {
        //when
        var exception = assertThrows(HeroByNameNotFoundException.class, () -> compareHeroes.compareHeroes("batman", "superma"));
        //then
        assertEquals("Hero not found: superma", exception.getMessage());
    }

}