package br.com.gubee.interview.model;

import br.com.gubee.interview.model.dto.HeroDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Data
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor
@Builder
public class PowerStats {

    private UUID id;
    private int strength;
    private int agility;
    private int dexterity;
    private int intelligence;
    private Instant createdAt;
    private Instant updatedAt;

    public PowerStats(HeroDTO heroDTO) {
        this.strength = heroDTO.getStrength();
        this.agility = heroDTO.getAgility();
        this.dexterity = heroDTO.getDexterity();
        this.intelligence = heroDTO.getIntelligence();
    }
}
