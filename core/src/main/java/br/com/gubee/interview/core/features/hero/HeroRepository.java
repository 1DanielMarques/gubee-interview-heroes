package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.model.Hero;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HeroRepository {
    Hero create(Hero hero);

    List<Hero> findAll();

    Hero findById(UUID id);

   Hero findByName(String name);

    Hero updateById(UUID id, Hero hero);

    void deleteById(UUID id);

    void deleteByName(String name);

    boolean exist(String name);

}
