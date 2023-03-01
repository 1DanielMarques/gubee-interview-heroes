package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.exception.HeroByIdNotFound;
import br.com.gubee.interview.core.exception.HeroByNameNotFound;
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
public class HeroService {

    private final CreateHero createHero;
    private final FindHero findHero;
    private final UpdateHero updateHero;
    private final DeleteHero deleteHero;
    private final CompareHeroes compareHeroes;
    private final CreatePowerStats createPowerStats;


    @Transactional
    public UUID create(HeroRequest heroRequest) {
        return createHero.create(heroRequest, createPowerStats.create(new PowerStats(heroRequest)));
    }


    public List<HeroRequest> findAll() {
        return findHero.findAll();
    }

    public HeroRequest findById(UUID id) {
        try {
            return findHero.findById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new HeroByIdNotFound(id);
        }
    }

    public HeroRequest findByName(String name) {
        try {
            return findHero.findByName(name);
        } catch (EmptyResultDataAccessException e) {
            throw new HeroByNameNotFound(name);
        }

    }

    @Transactional
    public HttpStatus updateById(UUID id, HeroRequest heroRequest) {
        try {
            updateHero.updateById(id, heroRequest);
            return HttpStatus.valueOf(200);
        } catch (EmptyResultDataAccessException e) {
            throw new HeroByIdNotFound(id);
        }
    }

    @Transactional
    public void deleteById(UUID id) {
        try {
            deleteHero.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new HeroByIdNotFound(id);
        }
    }

    public ComparedHeroes compareHeroes(String name_1, String name_2) {
        return compareHeroes.compareHeroes(name_1, name_2);
    }


}
