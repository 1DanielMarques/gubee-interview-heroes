package br.com.gubee.interview.domain.spi.hero;


import br.com.gubee.interview.domain.model.hero.Hero;

import java.util.List;
import java.util.UUID;

public interface HeroRepository {
    Hero create(Hero hero);

    List<Hero> findAll();

    Hero findById(UUID id);

    Hero findByName(String name);

    Hero updateHero(Hero hero);

    void deleteById(UUID id);

    void deleteByName(String name);

    boolean exist(String name);

}
