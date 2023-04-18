package br.com.gubee.interview.persistence.repositories.hero;

import br.com.gubee.interview.domain.exceptions.FailedCreateHeroException;
import br.com.gubee.interview.domain.exceptions.HeroByIdNotFoundException;
import br.com.gubee.interview.domain.exceptions.HeroByNameNotFoundException;
import br.com.gubee.interview.domain.exceptions.ResourceNotFoundException;
import br.com.gubee.interview.domain.model.hero.Hero;
import br.com.gubee.interview.domain.spi.hero.HeroRepository;
import br.com.gubee.interview.persistence.entities.HeroEntity;
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
    public Hero findById(UUID id) {
        try {
            return postgresRepository.findById(id).toHero();
        } catch (ResourceNotFoundException e) {
            throw new HeroByIdNotFoundException(id);
        }
    }

    @Override
    public Hero findByName(String name) {
        try {
            return postgresRepository.findByName(name).toHero();
        } catch (ResourceNotFoundException e) {
            throw new HeroByNameNotFoundException(name);
        }
    }

    @Override
    public Hero updateHero(Hero hero) {
        try {
            return postgresRepository.updateHero(HeroEntity.fromHero(hero)).toHero();
        } catch (ResourceNotFoundException e) {
            throw new HeroByIdNotFoundException(hero.getId());
        }

    }

    @Override
    public void deleteById(UUID id) {
        try {
            postgresRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new HeroByIdNotFoundException(id);
        }

    }

    @Override
    public void deleteByName(String name) {
        try {
            postgresRepository.deleteByName(name);
        } catch (ResourceNotFoundException e) {
            throw new HeroByNameNotFoundException(name);
        }
    }

    @Override
    public boolean exist(String name) {
        return postgresRepository.exist(name);
    }
}
