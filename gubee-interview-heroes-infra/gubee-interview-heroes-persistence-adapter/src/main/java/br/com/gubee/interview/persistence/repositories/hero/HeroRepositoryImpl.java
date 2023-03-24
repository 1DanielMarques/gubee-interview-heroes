package br.com.gubee.interview.persistence.repositories.hero;

import br.com.gubee.interview.domain.exceptions.FailedCreateHeroException;
import br.com.gubee.interview.domain.model.hero.Hero;
import br.com.gubee.interview.domain.repository.HeroRepository;
import br.com.gubee.interview.persistence.entities.HeroEntity;
import br.com.gubee.interview.domain.exceptions.HeroByIdNotFoundException;
import br.com.gubee.interview.domain.exceptions.HeroByNameNotFoundException;
import br.com.gubee.interview.domain.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class HeroRepositoryImpl implements HeroRepository {

    private final PostgresHeroRepository postgresRepository;


    @Override
    public Hero create(Hero hero) {
        try {
            return postgresRepository.create(HeroEntity.fromHero(hero)).toHero();
        } catch (ResourceNotFoundException e) {
            throw new FailedCreateHeroException();
        }

    }

    @Override
    public List<Hero> findAll() {
        return postgresRepository.findAll().stream().map(HeroEntity::toHero).toList();
    }

    @Override
    public Hero findById(UUID id) throws ResourceNotFoundException{
            return postgresRepository.findById(id).toHero();
    }

    @Override
    public Hero findByName(String name) throws ResourceNotFoundException{
            return postgresRepository.findByName(name).toHero();

    }

    @Override
    public Hero updateHero(Hero hero) throws ResourceNotFoundException{
            return postgresRepository.updateHero(HeroEntity.fromHero(hero)).toHero();
    }

    @Override
    public void deleteById(UUID id) throws ResourceNotFoundException{
            postgresRepository.deleteById(id);

    }

    @Override
    public void deleteByName(String name) throws ResourceNotFoundException{
            postgresRepository.deleteByName(name);
    }

    @Override
    public boolean exist(String name) {
        return postgresRepository.exist(name);
    }
}
