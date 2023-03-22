package br.com.gubee.interview.core.features.usecase.powerstats;

import br.com.gubee.interview.core.exception.PowerStatsByIdNotFoundException;
import br.com.gubee.interview.core.features.powerstats.PowerStatsRepository;
import br.com.gubee.interview.core.features.stub.PowerStatsRepositoryStub;
import br.com.gubee.interview.core.features.usecase.powerstats.interfaces.FindPowerStats;
import br.com.gubee.interview.model.PowerStats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class FindPowerStatsUseCaseTest {

    private final PowerStatsRepository powerStatsRepository = new PowerStatsRepositoryStub();
    private final FindPowerStats findPowerStats = new FindPowerStatsUseCase(powerStatsRepository);
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
    void shouldReturnPowerStatsById() {
        //when
        var powerStats = findPowerStats.findById(createdPowerStats.getId());
        //then
        assertEquals(createdPowerStats, powerStats);

    }

    @Test
    void shouldThrowExceptionIfPowerStatsByIdNotFound() {
        //given
        var powerStatsId = UUID.randomUUID();
        //when
        var exception = assertThrows(PowerStatsByIdNotFoundException.class, () -> findPowerStats.findById(powerStatsId));
        //then
        assertEquals("Power stats not found: " + powerStatsId, exception.getMessage());
    }

    @Test
    void shouldReturnListOfPowerStats() {
        //given
        var secondPowerStats = PowerStats.builder()
                .agility(6)
                .dexterity(5)
                .strength(4)
                .intelligence(3)
                .build();
        powerStatsRepository.create(secondPowerStats);
        //when
        List<PowerStats> powerStatsList = findPowerStats.findAll();
        //then
        assertTrue(powerStatsList.containsAll(Arrays.asList(createdPowerStats, secondPowerStats)));
    }

}
