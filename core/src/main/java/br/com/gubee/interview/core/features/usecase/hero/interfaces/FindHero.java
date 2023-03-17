package br.com.gubee.interview.core.features.usecase.hero.interfaces;

import br.com.gubee.interview.model.Hero;

import java.util.List;
import java.util.UUID;

public interface FindHero {

    List<Hero> findAll();

    Hero findById(UUID id);

    Hero findByName(String name);

}
