package br.com.gubee.interview.model;

import br.com.gubee.interview.model.enums.Race;
import br.com.gubee.interview.model.request.HeroRequest;
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

    public Hero(HeroRequest heroRequest, UUID powerStatsId) {
        this.name = heroRequest.getName();
        this.race = heroRequest.getRace();
        this.powerStatsId = powerStatsId;
    }
}
