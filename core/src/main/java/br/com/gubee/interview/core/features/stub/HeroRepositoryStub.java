package br.com.gubee.interview.core.features.stub;


import br.com.gubee.interview.core.exception.ResourceNotFoundException;
import br.com.gubee.interview.core.features.hero.HeroRepository;
import br.com.gubee.interview.model.Hero;

import java.time.Instant;
import java.util.*;

public class HeroRepositoryStub implements HeroRepository {

    final Map<UUID, Hero> inMemory = new HashMap<>();

    @Override
    public Hero create(Hero hero) {
        hero.setId(UUID.randomUUID());
        hero.setCreatedAt(Instant.now());
        hero.setUpdatedAt(Instant.now());
        inMemory.put(hero.getId(), hero);
        return inMemory.get(hero.getId());
    }

    @Override
    public List<Hero> findAll() {
        return inMemory.values().stream().toList();
    }

    @Override
    public Hero findById(UUID id) {
        return inMemory.get(id);
    }

    @Override
    public Hero findByName(String name)  {
        List<Hero> heroFound = inMemory.values().stream().filter(hero -> hero.getName().equals(name))
                .toList();
       // if (heroFound.size() == 0) throw new ResourceNotFoundException();
        return heroFound.get(0);
    }

    @Override
    public Hero updateById(UUID id, Hero heroToUpdate)  {
       // if (inMemory.get(id) == null) ;
        var olderHero = inMemory.get(id);
        heroToUpdate.setId(olderHero.getId());
        heroToUpdate.setCreatedAt(olderHero.getCreatedAt());
        heroToUpdate.setUpdatedAt(Instant.now());
        inMemory.put(heroToUpdate.getId(), heroToUpdate);
        return inMemory.get(heroToUpdate.getId());
    }


    @Override
    public void deleteById(UUID id)  {
        var hero = findById(id);
        inMemory.remove(hero.getId());

    }

    @Override
    public void deleteByName(String name) {
        //inMemory.remove(findByName(name).getId());
    }

    @Override
    public boolean exist(String name) {
        return inMemory.values().stream().anyMatch(hero -> hero.getName().equals(name));
    }
}
