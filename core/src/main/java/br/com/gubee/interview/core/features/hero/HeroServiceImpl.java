package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.exception.HeroByIdNotFoundException;
import br.com.gubee.interview.core.exception.HeroByNameNotFoundException;
import br.com.gubee.interview.core.features.usecase.interfaces.*;
import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.model.request.ComparedHeroes;
import br.com.gubee.interview.model.request.HeroRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HeroServiceImpl implements HeroService {

    private final CreateHero createHero;
    private final FindHero findHero;
    private final UpdateHero updateHero;
    private final DeleteHero deleteHero;
    private final CompareHeroes compareHeroes;
    private final CreatePowerStats createPowerStats;

    @Override
    @Transactional
    public HeroRequest create(HeroRequest heroRequest) {
        return createHero.create(heroRequest, createPowerStats.create(new PowerStats(heroRequest)));
    }

    @Override
    public List<HeroRequest> findAll() {
        return findHero.findAll();
    }

    @Override
    public HeroRequest findById(UUID id) {
        try {
            return findHero.findById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new HeroByIdNotFoundException(id);
        }
    }

    @Override
    public HeroRequest findByName(String name) {
        try {
            return findHero.findByName(name);
        } catch (EmptyResultDataAccessException e) {
            throw new HeroByNameNotFoundException(name);
        }
    }

    public UUID getHeroIdByName(String name) {
        return findHero.getHeroIdByName(name);
    }

    @Override
    @Transactional
    public HttpStatus updateById(UUID id, HeroRequest heroRequest) {
        try {
            updateHero.updateById(id, heroRequest);
            return HttpStatus.valueOf(200);
        } catch (EmptyResultDataAccessException e) {
            throw new HeroByIdNotFoundException(id);
        }
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        try {
            deleteHero.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new HeroByIdNotFoundException(id);
        }
    }


    @Transactional
    public void deleteByName(String name) {
        try {
            deleteHero.deleteById(getHeroIdByName(name));
        } catch (EmptyResultDataAccessException e) {
            throw new HeroByNameNotFoundException(name);
        }
    }

    @Override
    public ComparedHeroes compareHeroes(String firstHero, String secondHero) {
        return compareHeroes.compareHeroes(firstHero, secondHero);
    }


}
