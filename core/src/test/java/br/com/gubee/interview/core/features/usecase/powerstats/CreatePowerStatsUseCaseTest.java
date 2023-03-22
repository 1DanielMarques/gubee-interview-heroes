package br.com.gubee.interview.core.features.usecase.powerstats;

import br.com.gubee.interview.core.features.usecase.powerstats.interfaces.CreatePowerStats;
import br.com.gubee.interview.model.PowerStats;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreatePowerStatsUseCaseTest {

    @Autowired
    private CreatePowerStats createPowerStats;

    @Test
    @DisplayName("Should create PowerStats and return its id")
    void shouldCreatePowerStatsAndReturnId() {
        //given
        var powerStats = PowerStats.builder()
                .agility(5)
                .dexterity(8)
                .strength(6)
                .intelligence(10)
                .build();
        //when
        var powerStatsId =  createPowerStats.create(powerStats);
        //then
        Assertions.assertSame(UUID.class, powerStatsId);
    }

    @Test
    @DisplayName("Should create PowerStats and return its id")
    void shouldCreatePowerStatsAndReturnId2() {
       // createPowerStats = new CreatePowerStatsUseCase(new HeroRepositoryStub());

        //given
        var powerStats = PowerStats.builder()
                .agility(5)
                .dexterity(8)
                .strength(6)
                .intelligence(10)
                .build();
        //when
        var powerStatsId =  createPowerStats.create(powerStats);
        //then
        Assertions.assertSame(UUID.class, powerStatsId);
    }
}