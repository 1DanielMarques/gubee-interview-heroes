package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.exception.ResourceNotFoundException;
import br.com.gubee.interview.model.Hero;

import java.util.List;
import java.util.UUID;

public interface HeroRepository {
    Hero create(Hero hero);

    List<Hero> findAll();

    Hero findById(UUID id) throws ResourceNotFoundException;

    Hero findByName(String name) throws ResourceNotFoundException;

    Hero updateById(UUID id, Hero hero) throws ResourceNotFoundException;

    void deleteById(UUID id) throws ResourceNotFoundException;

    void deleteByName(String name) throws ResourceNotFoundException;

    boolean exist(String name);

}
