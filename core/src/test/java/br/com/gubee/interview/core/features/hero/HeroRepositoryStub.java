package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.model.request.HeroRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class HeroRepositoryStub implements HeroRepository {

    private final Map<UUID, Hero> heroesMap = new HashMap<>();
    private final Map<UUID, PowerStats> powerStatsMap = new HashMap<>();

    private void savePowerStats(UUID powerStatsId) {
        PowerStats powerStats = PowerStats.builder()
                .id(powerStatsId)
                .agility(5)
                .dexterity(8)
                .strength(6)
                .intelligence(10)
                .build();
        powerStatsMap.put(powerStatsId, powerStats);
    }

    private HeroRequest buildHeroRequest(UUID heroId, UUID powerStatsId) {
        Hero hero = heroesMap.get(heroId);
        PowerStats powerStats = powerStatsMap.get(powerStatsId);
        return HeroRequest.builder()
                .id(hero.getId())
                .name(hero.getName())
                .race(hero.getRace())
                .agility(powerStats.getAgility())
                .dexterity(powerStats.getDexterity())
                .strength(powerStats.getStrength())
                .intelligence(powerStats.getIntelligence())
                .build();

    }

    @Override
    public HeroRequest create(Hero hero) {
        savePowerStats(hero.getPowerStatsId());
        hero.setId(UUID.randomUUID());
        heroesMap.put(hero.getId(), hero);
        return buildHeroRequest(hero.getId(), hero.getPowerStatsId());
    }

    @Override
    public List<HeroRequest> findAll() {
        return null;
    }

    @Override
    public HeroRequest findById(UUID id) {
        Hero hero = heroesMap.get(id);
        return buildHeroRequest(hero.getId(), powerStatsMap.get(hero.getPowerStatsId()).getId());
    }

    @Override
    public HeroRequest findByName(String name) {
        return null;
    }

    @Override
    public void updateById(UUID id, HeroRequest heroRequest) {

    }

    @Override
    public void deleteById(UUID id) {

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
                .dexterity(4)
                .strength(10)
                .intelligence(8)
                .build();
        return Map.of(firstHeroId, powerStatsFirstHero, secondHeroId, powerStatsSecondHero);
    }
}
