package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.exception.ResourceNotFoundException;
import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.entities.HeroEntity;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class HeroRepositoryImpl implements HeroRepository {

    private final PostgresHeroRepository postgresRepository;


    @Override
    public Hero create(Hero hero) {
        return postgresRepository.create(HeroEntity.fromHero(hero)).toHero();
    }

    @Override
    public List<Hero> findAll() {
        return postgresRepository.findAll().stream().map(entity -> entity.toHero()).toList();
    }

    @Override
    public Hero findById(UUID id) throws ResourceNotFoundException {
        return postgresRepository.findById(id).toHero();
    }

    @Override
    public Hero findByName(String name) throws ResourceNotFoundException {
        return postgresRepository.findByName(name).toHero();
    }

    @Override
    public Hero updateById(UUID id, Hero hero) throws ResourceNotFoundException {
        return postgresRepository.updateById(id, HeroEntity.fromHero(hero)).toHero();
    }

    @Override
    public void deleteById(UUID id) throws ResourceNotFoundException {
        postgresRepository.deleteById(id);
    }

    @Override
    public void deleteByName(String name) throws ResourceNotFoundException {
        postgresRepository.deleteByName(name);
    }

    @Override
    public boolean exist(String name) {
        return postgresRepository.exist(name);
    }
}
