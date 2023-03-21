package br.com.gubee.interview.core.features.usecase;

import br.com.gubee.interview.core.features.hero.HeroRepository;
import br.com.gubee.interview.core.features.powerstats.PowerStatsRepository;
import br.com.gubee.interview.core.features.stub.HeroRepositoryStub;
import br.com.gubee.interview.core.features.stub.PowerStatsRepositoryStub;
import br.com.gubee.interview.core.features.usecase.hero.CompareHeroesUseCase;
import br.com.gubee.interview.core.features.usecase.hero.interfaces.CompareHeroes;
import br.com.gubee.interview.model.ComparedHeroes;
import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.model.enums.Race;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CompareHeroesUseCaseTest {

    private final HeroRepository heroRepositoryStub = new HeroRepositoryStub();
    private final PowerStatsRepository powerStatsRepositoryStub = new PowerStatsRepositoryStub();
    private final CompareHeroes compareHeroes = new CompareHeroesUseCase(heroRepositoryStub, powerStatsRepositoryStub);


    @Test
    @DisplayName("Should compare two heroes attributes and return them")
    void shouldCompareHeroesAttributesAndReturnThem() {
        //Given
        var firstPowerStats = PowerStats.builder()
                .agility(5)
                .dexterity(8)
                .strength(6)
                .intelligence(10)
                .build();
        powerStatsRepositoryStub.create(firstPowerStats);
        var firstHero = Hero.builder()
                .name("batman")
                .race(Race.HUMAN)
                .powerStatsId(firstPowerStats.getId())
                .build();
        heroRepositoryStub.create(firstHero);

        var secondPowerStats = PowerStats.builder()
                .agility(7)
                .dexterity(4)
                .strength(10)
                .intelligence(8)
                .build();
        powerStatsRepositoryStub.create(secondPowerStats);
        var secondHero = Hero.builder()
                .name("superman")
                .race(Race.ALIEN)
                .powerStatsId(secondPowerStats.getId())
                .build();
        heroRepositoryStub.create(secondHero);


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

}