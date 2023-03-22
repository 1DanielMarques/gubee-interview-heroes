package br.com.gubee.interview.core.features.usecase.powerstats;

import br.com.gubee.interview.core.exception.PowerStatsByIdNotFoundException;
import br.com.gubee.interview.core.exception.ResourceNotFoundException;
import br.com.gubee.interview.core.features.powerstats.PowerStatsRepository;
import br.com.gubee.interview.core.features.stub.PowerStatsRepositoryStub;
import br.com.gubee.interview.core.features.usecase.powerstats.interfaces.UpdatePowerStats;
import br.com.gubee.interview.model.PowerStats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class UpdatePowerStatsUseCaseTest {

    private final PowerStatsRepository powerStatsRepository = new PowerStatsRepositoryStub();
    private final UpdatePowerStats updatePowerStats = new UpdatePowerStatsUseCase(powerStatsRepository);

    private PowerStats createdPowerStats;

    @BeforeEach
    void setup() {
        createdPowerStats = powerStatsRepository.create(PowerStats.builder()
                .agility(3)
                .dexterity(4)
                .strength(5)
                .intelligence(6)
                .build());
    }

    @Test
    void shouldUpdatePowerStatsAndReturnIt() throws ResourceNotFoundException {
        //given
        var powerStatsToUpdate = PowerStats.builder()
                .agility(7)
                .dexterity(7)
                .strength(7)
                .intelligence(7)
                .build();
        //when
        updatePowerStats.updateById(createdPowerStats.getId(), powerStatsToUpdate);
        //then
        var updatedPowerStats = powerStatsRepository.findById(createdPowerStats.getId());
        assertNotEquals(updatedPowerStats, createdPowerStats);
    }

    @Test
    void shouldThrowExceptionIfPowerStatsIdNotFound() {
        //given
        var powerStatsToUpdate = PowerStats.builder()
                .agility(7)
                .dexterity(7)
                .strength(7)
                .intelligence(7)
                .build();
        var powerStatsId = UUID.randomUUID();
        //when
        var exception = assertThrows(PowerStatsByIdNotFoundException.class, () -> updatePowerStats.updateById(powerStatsId, powerStatsToUpdate));
        //then
        assertEquals("Power stats not found: " + powerStatsId,exception.getMessage());
    }

}
