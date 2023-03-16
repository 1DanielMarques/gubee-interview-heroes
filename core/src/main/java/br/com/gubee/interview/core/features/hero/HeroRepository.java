package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.model.entities.HeroEntity;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface HeroRepository {
    HeroEntity create(HeroEntity hero);

    List<HeroEntity> findAll();

    HeroEntity findById(UUID id);

    HeroEntity findByName(String name);

    HeroEntity updateById(UUID id, HeroEntity hero);

    void deleteById(UUID id);

    void deleteByName(String name);

    UUID getHeroIdByName(String name);

    Map<UUID, PowerStats> compareHeroes(UUID firstHeroId, UUID secondHeroId);

}
