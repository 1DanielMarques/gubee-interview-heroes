package br.com.gubee.interview.core.features.usecase.powerstats;

import br.com.gubee.interview.core.exception.ResourceNotFoundException;
import br.com.gubee.interview.core.features.powerstats.PowerStatsRepository;
import br.com.gubee.interview.core.features.stub.PowerStatsRepositoryStub;
import br.com.gubee.interview.core.features.usecase.powerstats.interfaces.CreatePowerStats;
import br.com.gubee.interview.model.PowerStats;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreatePowerStatsUseCaseTest {

    private final PowerStatsRepository powerStatsRepository = new PowerStatsRepositoryStub();
    private final CreatePowerStats createPowerStats = new CreatePowerStatsUseCase(powerStatsRepository);

    @Test
    void shouldCreatePowerStatsAndReturnIt() throws ResourceNotFoundException {
        //given
        var powerStatsToBeCreated = PowerStats.builder()
                .agility(3)
                .dexterity(4)
                .strength(5)
                .intelligence(6)
                .build();
        //when
        var powerStatsId = createPowerStats.create(powerStatsToBeCreated).getId();
        //then
        var powerStats = powerStatsRepository.findById(powerStatsId);
        Assertions.assertEquals(powerStats, powerStatsToBeCreated);

    }

}
