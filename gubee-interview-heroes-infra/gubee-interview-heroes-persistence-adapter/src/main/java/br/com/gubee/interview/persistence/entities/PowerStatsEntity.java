package br.com.gubee.interview.persistence.entities;

import br.com.gubee.interview.domain.model.powerstats.PowerStats;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PowerStatsEntity {
    private UUID id;
    private Integer strength;
    private Integer agility;
    private Integer dexterity;
    private Integer intelligence;
    private Instant createdAt;
    private Instant updatedAt;


    public static PowerStatsEntity fromPowerStats(PowerStats powerStats) {
        PowerStatsEntity powerStatsEntity = new PowerStatsEntity();
        powerStatsEntity.updateData(powerStats);
        return powerStatsEntity;
    }

    private void updateData(PowerStats powerStats) {
        this.id = powerStats.getId();
        this.strength = powerStats.getStrength();
        this.agility = powerStats.getAgility();
        this.dexterity = powerStats.getDexterity();
        this.intelligence = powerStats.getIntelligence();
    }

    public PowerStats toPowerStats() {
        PowerStats powerStats = PowerStats.builder()
                .id(this.id)
                .strength(this.strength)
                .agility(this.agility)
                .dexterity(this.dexterity)
                .intelligence(this.intelligence)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
        return powerStats;
    }


}
