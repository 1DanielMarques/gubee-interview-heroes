package br.com.gubee.interview.domain.api.hero;


import br.com.gubee.interview.domain.model.hero.Hero;

import java.util.List;
import java.util.UUID;

public interface FindHero {

    List<Hero> findAll();

    Hero findById(UUID id);

    Hero findByName(String name);

}
