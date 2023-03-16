package br.com.gubee.interview.model;

import br.com.gubee.interview.model.enums.Race;
import br.com.gubee.interview.model.dto.HeroDTO;
import lombok.*;

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
        this.race = heroDTO.getRace();
        this.powerStatsId = powerStatsId;
    }

    public boolean getEnabled(){
        return this.enabled;
    }
}
