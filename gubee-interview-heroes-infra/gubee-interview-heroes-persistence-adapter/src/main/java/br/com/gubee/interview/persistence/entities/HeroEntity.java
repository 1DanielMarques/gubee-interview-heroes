package br.com.gubee.interview.persistence.entities;


import br.com.gubee.interview.domain.enums.Race;
import br.com.gubee.interview.domain.model.hero.Hero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
public class HeroEntity {

    private UUID id;
    private String name;
    private Race race;
    private UUID powerStatsId;
    private Instant createdAt;
    private Instant updatedAt;
    private boolean enabled;


    public static HeroEntity fromHero(Hero hero) {
        HeroEntity heroEntity = new HeroEntity();
        heroEntity.updateData(hero);
        return heroEntity;
    }

    private void updateData(Hero hero) {
        this.id = hero.getId();
        this.name = hero.getName();
        this.race = hero.getRace();
        this.powerStatsId = hero.getPowerStatsId();
    }

    public Hero toHero() {
        Hero hero = Hero.builder()
                .id(this.id)
                .name(this.name)
                .race(this.race)
                .powerStatsId(this.powerStatsId)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .enabled(this.enabled)
                .build();
        return hero;
    }


}
