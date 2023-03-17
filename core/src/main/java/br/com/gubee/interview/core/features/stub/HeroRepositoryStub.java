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
        return null;
    }

    @Override
    public List<Hero> findAll() {
        return null;
    }

    @Override
    public Hero findById(UUID id) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public Hero findByName(String name) throws ResourceNotFoundException {
        return null;
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
        return false;
    }
}
