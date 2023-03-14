package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.model.request.HeroRequest;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface HeroRepository {
    HeroRequest create(Hero hero);

    List<HeroRequest> findAll();

    HeroRequest findById(UUID id);

    HeroRequest findByName(String name);

    void updateById(UUID id, HeroRequest heroRequest);

    void deleteById(UUID id);

    UUID getHeroIdByName(String name);

    Map<UUID, PowerStats> compareHeroes(UUID firstHeroId, UUID secondHeroId);

}
