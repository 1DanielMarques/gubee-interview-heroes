package br.com.gubee.interview.core.features.hero.resource.facade;

import br.com.gubee.interview.core.exception.HeroByIdNotFoundException;
import br.com.gubee.interview.core.exception.HeroByNameNotFoundException;
import br.com.gubee.interview.core.features.hero.resource.assembler.Assembler;
import br.com.gubee.interview.core.features.usecase.hero.interfaces.*;
import br.com.gubee.interview.model.ComparedHeroes;
import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.model.dto.HeroDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HeroFacade {

    private final Assembler assembler = new Assembler();
    private final CreateHero createHero;
    private final FindHero findHero;
    private final UpdateHero updateHero;
    private final DeleteHero deleteHero;
    private final CompareHeroes compareHeroes;
    private final PowerStatsFacade powerStatsFacade;


    public HeroDTO create(HeroDTO heroDto) {
        var powerStats = powerStatsFacade.create(assembler.toPowerStatsDomain(heroDto));
        var hero = createHero.create(assembler.toHeroDomain(heroDto, powerStats.getId()));
        return assembler.toHeroDTO(hero, powerStats);
    }


    public List<HeroDTO> findAll() {
        List<PowerStats> powerStatsList = powerStatsFacade.findAll();
        List<Hero> heroList = findHero.findAll();
        List<HeroDTO> heroDTOList = assembler.toDTOList(heroList, powerStatsList);
        return heroDTOList;
    }

        // Throws exception aqui? Ou tratar a exceção aqui
    public HeroDTO findById(UUID id) {
        var hero = findHero.findById(id);
        var powerStats = powerStatsFacade.findById(hero.getPowerStatsId());
        return assembler.toHeroDTO(hero, powerStats);
    }


    public HeroDTO findByName(String name) throws HeroByNameNotFoundException {
        var hero = findHero.findByName(name);
        var powerStats = powerStatsFacade.findById(hero.getPowerStatsId());
        return assembler.toHeroDTO(hero, powerStats);
    }

    public UUID getHeroIdByName(String name) {
        return findHero.getHeroIdByName(name);
    }

    public HttpStatus updateById(UUID id, HeroDTO heroDTO) {
        try {
            updateHero.updateById(id, heroDTO);
            return HttpStatus.valueOf(200);
        } catch (EmptyResultDataAccessException e) {
            throw new HeroByIdNotFoundException(id);
        }
    }


    public void deleteById(UUID id) {
        try {
            deleteHero.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new HeroByIdNotFoundException(id);
        }
    }


    public void deleteByName(String name) {
        try {
            deleteHero.deleteById(getHeroIdByName(name));
        } catch (EmptyResultDataAccessException e) {
            throw new HeroByNameNotFoundException(name);
        }
    }


    public ComparedHeroes compareHeroes(String firstHero, String secondHero) {
        return compareHeroes.compareHeroes(firstHero, secondHero);
    }


}
