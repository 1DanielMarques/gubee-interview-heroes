package br.com.gubee.interview.core.features.usecase.hero.interfaces;

import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.dto.HeroDTO;

import java.util.List;
import java.util.UUID;

public interface FindHero {

    List<Hero> findAll();

    Hero findById(UUID id);

    HeroDTO findByName(String name);

    UUID getHeroIdByName(String name);
}
