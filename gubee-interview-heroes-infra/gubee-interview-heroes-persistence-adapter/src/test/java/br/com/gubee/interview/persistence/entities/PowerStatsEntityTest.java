package br.com.gubee.interview.persistence.entities;

import br.com.gubee.interview.domain.model.powerstats.PowerStats;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

public class PowerStatsEntityTest {


    @Test
    void shouldReturnEntityFromDomain() {
        //given
        var powerStats = PowerStats.builder()
                .agility(3)
                .dexterity(4)
                .strength(5)
                .intelligence(6)
                .build();
        //when
        var powerStatsEntity = PowerStatsEntity.fromPowerStats(powerStats);
        //then
        assertThat(powerStatsEntity).usingRecursiveComparison().isEqualTo(powerStats);
    }

    @Test
    void shouldReturnDomainFromEntity() {
        //given
        var powerStatsEntity = new PowerStatsEntity(null, 5, 3, 4, 6, Instant.now(), Instant.now());
        //when
        var powerStats = powerStatsEntity.toPowerStats();
        //then
        assertThat(powerStats).usingRecursiveComparison().isEqualTo(powerStatsEntity);
    }


}
