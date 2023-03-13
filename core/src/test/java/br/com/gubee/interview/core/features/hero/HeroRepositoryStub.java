package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.model.PowerStats;

import java.util.Map;
import java.util.UUID;

public class HeroRepositoryStub extends HeroRepository {

    public HeroRepositoryStub() {

    }

    @Override
    public UUID getHeroIdByName(String name) {
        return UUID.randomUUID();
    }

    @Override
    public Map<UUID, PowerStats> compareHeroes(UUID firstHeroId, UUID secondHeroId) {
        PowerStats powerStatsFirstHero = PowerStats.builder()
                .agility(5)
                .dexterity(8)
                .strength(6)
                .intelligence(10)
                .build();

        PowerStats powerStatsSecondHero = PowerStats.builder()
                .agility(7)
                .dexterity(6)
                .strength(8)
                .intelligence(7)
                .build();
        return Map.of(firstHeroId, powerStatsFirstHero, secondHeroId, powerStatsSecondHero);
    }

}
