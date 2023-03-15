package br.com.gubee.interview.core.features.hero.stub;

import br.com.gubee.interview.core.features.hero.HeroRepository;
import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.model.request.HeroRequest;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class HeroRepositoryStub implements HeroRepository {


    @Override
    public HeroRequest create(Hero hero) {
        return null;
    }

    @Override
    public List<HeroRequest> findAll() {
        return null;
    }

    @Override
    public HeroRequest findById(UUID id) {
        return null;
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
