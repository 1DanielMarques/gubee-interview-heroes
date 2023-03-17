package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.model.entities.HeroEntity;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface HeroRepository {
    HeroEntity create(HeroEntity hero) throws EmptyResultDataAccessException;

    List<HeroEntity> findAll();

    HeroEntity findById(UUID id) throws EmptyResultDataAccessException;

    HeroEntity findByName(String name)  throws EmptyResultDataAccessException;

    HeroEntity updateById(UUID id, HeroEntity hero) throws EmptyResultDataAccessException;

    void deleteById(UUID id) throws EmptyResultDataAccessException;

    void deleteByName(String name) throws EmptyResultDataAccessException;

    Map<UUID, PowerStats> compareHeroes(UUID firstHeroId, UUID secondHeroId);

}
