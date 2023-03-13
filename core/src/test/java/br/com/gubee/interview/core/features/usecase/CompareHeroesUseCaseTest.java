package br.com.gubee.interview.core.features.usecase;

import br.com.gubee.interview.core.features.hero.HeroRepository;
import br.com.gubee.interview.core.features.hero.HeroRepositoryStub;
import br.com.gubee.interview.core.features.usecase.interfaces.CompareHeroes;
import br.com.gubee.interview.model.request.ComparedHeroes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class CompareHeroesUseCaseTest {

    private HeroRepository heroRepositoryStub;


    @BeforeEach
    void setup() {
        heroRepositoryStub = new HeroRepositoryStub();
    }


    @Test
    void compareHeroes() {
        CompareHeroes compareHeroes = new CompareHeroesUseCase(heroRepositoryStub);
        ComparedHeroes result = compareHeroes.compareHeroes("Batman", "Superman");

        ComparedHeroes expected = ComparedHeroes.builder()
                .firstId(result.getFirstId())
                .firstAgility(-5)
                .firstDexterity(8)
                .firstStrength(-6)
                .firstIntelligence(10)

                .secondId(result.getSecondId())
                .secondAgility(7)
                .secondDexterity(-6)
                .secondStrength(8)
                .secondIntelligence(-7)
                .build();
        Assertions.assertEquals(expected, result);

    }

}
