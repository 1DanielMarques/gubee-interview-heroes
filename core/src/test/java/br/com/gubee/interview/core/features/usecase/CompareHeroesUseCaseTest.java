package br.com.gubee.interview.core.features.usecase;

import br.com.gubee.interview.core.features.hero.HeroRepository;
import br.com.gubee.interview.core.features.hero.HeroRepositoryStub;
import br.com.gubee.interview.core.features.usecase.interfaces.CompareHeroes;
import br.com.gubee.interview.model.request.ComparedHeroes;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


@RequiredArgsConstructor
public class CompareHeroesUseCaseTest {

    private HeroRepository heroRepositoryStub;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;



    @Test
    void compareHeroes() {
        heroRepositoryStub = new HeroRepositoryStub(namedParameterJdbcTemplate);
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
                .secondDexterity(-4)
                .secondStrength(10)
                .secondIntelligence(-8)
                .build();
        Assertions.assertEquals(expected, result);

    }

}
