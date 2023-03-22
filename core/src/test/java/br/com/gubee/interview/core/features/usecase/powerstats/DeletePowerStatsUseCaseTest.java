package br.com.gubee.interview.core.features.usecase.powerstats;

import br.com.gubee.interview.core.exception.PowerStatsByIdNotFoundException;
import br.com.gubee.interview.core.exception.ResourceNotFoundException;
import br.com.gubee.interview.core.features.powerstats.PowerStatsRepository;
import br.com.gubee.interview.core.features.stub.PowerStatsRepositoryStub;
import br.com.gubee.interview.core.features.usecase.powerstats.interfaces.DeletePowerStats;
import br.com.gubee.interview.model.PowerStats;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeletePowerStatsUseCaseTest {
    private final PowerStatsRepository powerStatsRepository = new PowerStatsRepositoryStub();
    private final DeletePowerStats deletePowerStats = new DeletePowerStatsUseCase(powerStatsRepository);

    @Test
    void shouldDeletePowerStatsById() {
        //given
        var powerStats = powerStatsRepository.create(PowerStats.builder()
                .agility(3)
                .dexterity(4)
                .strength(5)
                .intelligence(6)
                .build());
        //when
        deletePowerStats.deleteById(powerStats.getId());
        //then
        assertThrows(ResourceNotFoundException.class, () -> powerStatsRepository.findById(powerStats.getId()));
    }

    @Test
    void shouldThrowExceptionIfPowerStatsIdNotFound(){
        //given
        var powerStatsId = UUID.randomUUID();
        //when
        var exception = assertThrows(PowerStatsByIdNotFoundException.class,()->deletePowerStats.deleteById(powerStatsId));
        //then
        assertEquals("Power stats not found: " + powerStatsId,exception.getMessage());
    }
}
