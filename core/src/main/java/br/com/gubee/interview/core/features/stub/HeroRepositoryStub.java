package br.com.gubee.interview.core.features.stub;


import br.com.gubee.interview.core.exception.ResourceNotFoundException;
import br.com.gubee.interview.core.features.hero.HeroRepository;
import br.com.gubee.interview.model.Hero;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class HeroRepositoryStub implements HeroRepository {

    final Map<UUID, Hero> inMemory = new HashMap<>();

    @Override
    public Hero create(Hero hero) {
        hero.setId(UUID.randomUUID());
        inMemory.put(hero.getId(), hero);
        return inMemory.get(hero.getId());
    }

    @Override
    public List<Hero> findAll() {
        return null;
    }

    @Override
    public Hero findById(UUID id) throws ResourceNotFoundException {
        var hero = inMemory.get(id);
        if (hero == null) throw new ResourceNotFoundException();
        return hero;
    }

    @Override
    public Hero findByName(String name) throws ResourceNotFoundException {
        List<Hero> heroFound = inMemory.values().stream().filter(hero -> hero.getName().equals(name))
                .toList();
        if (heroFound.size() == 0) throw new ResourceNotFoundException();
        return heroFound.get(0);
    }

    @Override
    public Hero updateById(UUID id, Hero hero) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public void deleteById(UUID id) throws ResourceNotFoundException {

    }

    @Override
    public void deleteByName(String name) throws ResourceNotFoundException {

    }

    @Override
    public boolean exist(String name) {
        return inMemory.values().stream().anyMatch(hero -> hero.getName().equals(name));
    }
}
