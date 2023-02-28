package br.com.gubee.interview.model;

import br.com.gubee.interview.model.request.HeroRequest;
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

    public PowerStats(HeroRequest heroRequest) {
        this.strength = heroRequest.getStrength();
        this.agility = heroRequest.getAgility();
        this.dexterity = heroRequest.getDexterity();
        this.intelligence = heroRequest.getIntelligence();
    }
}
