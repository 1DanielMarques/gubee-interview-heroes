package br.com.gubee.interview.domain.usecase.hero;

import br.com.gubee.interview.domain.enums.Race;
import br.com.gubee.interview.domain.model.hero.ComparedHeroes;
import br.com.gubee.interview.domain.model.hero.Hero;
import br.com.gubee.interview.domain.model.powerstats.PowerStats;
import br.com.gubee.interview.domain.repository.HeroRepository;
import br.com.gubee.interview.domain.repository.HeroRepositoryStub;
import br.com.gubee.interview.domain.repository.PowerStatsRepository;
import br.com.gubee.interview.domain.repository.PowerStatsRepositoryStub;
import br.com.gubee.interview.domain.usecase.hero.interfaces.CompareHeroes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(expected, result);
    }

}
