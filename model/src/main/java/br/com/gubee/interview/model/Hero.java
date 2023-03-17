package br.com.gubee.interview.model;

import br.com.gubee.interview.model.dto.HeroDTO;
import br.com.gubee.interview.model.enums.Race;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
public class Hero {

    private UUID id;
    private String name;
    private Race race;
    private UUID powerStatsId;
    private Instant createdAt;
    private Instant updatedAt;
    private boolean enabled;

    public Hero(HeroDTO heroDTO, UUID powerStatsId) {
        this.name = heroDTO.getName();
        this.race = switch (heroDTO.getRace()) {
            case "HUMAN" -> Race.HUMAN;
            case "ALIEN" -> Race.ALIEN;
            case "DIVINE" -> Race.DIVINE;
            case "CYBORG" -> Race.CYBORG;
            default -> throw new IllegalArgumentException("Invalid Race of Hero: " + heroDTO.getRace());
        };
        this.powerStatsId = powerStatsId;
    }

    public boolean getEnabled() {
        return this.enabled;
    }
}
