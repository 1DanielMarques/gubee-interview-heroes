package br.com.gubee.interview.domain.repository;


import br.com.gubee.interview.domain.exceptions.ResourceNotFoundException;
import br.com.gubee.interview.domain.model.hero.Hero;

import java.util.List;
import java.util.UUID;

public interface HeroRepository {
    Hero create(Hero hero);

    List<Hero> findAll();

    Hero findById(UUID id) throws ResourceNotFoundException;

    Hero findByName(String name) throws ResourceNotFoundException;

    Hero updateHero(Hero hero) throws ResourceNotFoundException;

    void deleteById(UUID id) throws ResourceNotFoundException;

    void deleteByName(String name) throws ResourceNotFoundException;

    boolean exist(String name);

}
