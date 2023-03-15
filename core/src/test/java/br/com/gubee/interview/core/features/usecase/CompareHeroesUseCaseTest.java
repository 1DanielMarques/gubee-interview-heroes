package br.com.gubee.interview.core.features.usecase;

import br.com.gubee.interview.core.features.hero.HeroRepository;
import br.com.gubee.interview.core.features.hero.stub.HeroRepositoryStub;
import br.com.gubee.interview.core.features.usecase.interfaces.CompareHeroes;
import br.com.gubee.interview.model.request.ComparedHeroes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class CompareHeroesUseCaseTest {

    private final HeroRepository heroRepositoryStub = new HeroRepositoryStub();
    private final CompareHeroes compareHeroes = new CompareHeroesUseCase(heroRepositoryStub);

    @Test
    void shouldCompareHeroesAttributesAndReturnThem() {
        ComparedHeroes result = compareHeroes.compareHeroes("Batman", "Superman");
        ComparedHeroes expected = ComparedHeroes.builder()
                .firstId(result.getFirstId())
                .firstAgility(-5)
                .firstDexterity(8)
                .firstStrength(-6)
                .firstIntelligence(10)

                .secondId(result.getSecondId())
                .secondAgility(7)
                .secondDexterity(-4)
                .secondStrength(10)
                .secondIntelligence(-8)
                .build();
        Assertions.assertEquals(expected, result);

    }

}
