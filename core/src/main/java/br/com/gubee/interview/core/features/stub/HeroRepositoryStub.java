package br.com.gubee.interview.core.features.stub;

/*
public class HeroRepositoryStub implements HeroRepository {

    private Map<UUID, Hero> heroInMemory = new HashMap<>();

    @Override
    public HeroRequest create(Hero hero) {
        UUID heroId = hero.getId();
        if (heroId == null) {
            heroId = UUID.randomUUID();
        }
        heroInMemory.put(heroId, hero);
        HeroRequest
        return ;
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
*/