package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.exception.HeroByIdNotFoundException;
import br.com.gubee.interview.core.exception.ResourceNotFoundException;
import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.model.entities.HeroEntity;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface HeroRepository {
    HeroEntity create(HeroEntity hero);

    List<HeroEntity> findAll();

    HeroEntity findById(UUID id) throws ResourceNotFoundException;

    HeroEntity findByName(String name) throws ResourceNotFoundException;

    HeroEntity updateById(UUID id, HeroEntity hero) throws ResourceNotFoundException;

    void deleteById(UUID id) throws ResourceNotFoundException;

    void deleteByName(String name) throws ResourceNotFoundException;

    Map<UUID, PowerStats> compareHeroes(UUID firstHeroId, UUID secondHeroId);

    boolean exist(String name);

}
