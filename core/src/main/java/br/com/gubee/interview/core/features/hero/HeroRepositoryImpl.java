package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.exception.HeroByIdNotFoundException;
import br.com.gubee.interview.core.exception.HeroByNameNotFoundException;
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
        try {
            return postgresRepository.create(HeroEntity.fromHero(hero)).toHero();
        } catch (ResourceNotFoundException e) {
            //throw new ErrorCreatingHeroException();
            throw new RuntimeException();
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
    public Hero updateById(UUID id, Hero hero) {
        //return postgresRepository.updateById(id, HeroEntity.fromHero(hero)).toHero();
        // return postgresRepository.save(id,HeroEntity.fromHero(hero)).toHero();
        // dar um Save
        return null;
    }

    @Override
    public void deleteById(UUID id) {
        try{
            postgresRepository.deleteById(id);
        }catch (ResourceNotFoundException e){
            throw new HeroByIdNotFoundException(id);
        }

    }

    @Override
    public void deleteByName(String name) {
        try{
            postgresRepository.deleteByName(name);
        }catch (ResourceNotFoundException e){
            throw new HeroByNameNotFoundException(name);
        }
    }

    @Override
    public boolean exist(String name) {
        return postgresRepository.exist(name);
    }
}
