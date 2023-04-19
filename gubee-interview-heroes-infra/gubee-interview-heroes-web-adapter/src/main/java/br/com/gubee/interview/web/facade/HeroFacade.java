package br.com.gubee.interview.web.facade;

import br.com.gubee.interview.domain.api.hero.*;
import br.com.gubee.interview.domain.model.hero.ComparedHeroes;
import br.com.gubee.interview.domain.model.hero.Hero;
import br.com.gubee.interview.domain.model.powerstats.PowerStats;
import br.com.gubee.interview.web.assembler.Assembler;
import br.com.gubee.interview.web.dto.ComparedHeroesDTO;
import br.com.gubee.interview.web.dto.HeroDTO;
import lombok.RequiredArgsConstructor;
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


    public HeroDTO create(HeroDTO heroDTO) {
        heroDTO.setName(heroDTO.getName().toUpperCase());
        var powerStats = powerStatsFacade.create(assembler.toPowerStatsDomain(heroDTO));
        var hero = createHero.create(assembler.toHeroDomain(heroDTO, powerStats.getId()));
        return assembler.toHeroDTO(hero, powerStats);
    }


    public List<HeroDTO> findAll() {
        List<PowerStats> powerStatsList = powerStatsFacade.findAll();
        List<Hero> heroList = findHero.findAll();
        List<HeroDTO> heroDTOList = assembler.toDTOList(heroList, powerStatsList);
        return heroDTOList;
    }

    public HeroDTO findById(UUID id) {
        var hero = findHero.findById(id);
        var powerStats = powerStatsFacade.findById(hero.getPowerStatsId());
        return assembler.toHeroDTO(hero, powerStats);
    }


    public HeroDTO findByName(String name) {
        var hero = findHero.findByName(name.toUpperCase());
        var powerStats = powerStatsFacade.findById(hero.getPowerStatsId());
        return assembler.toHeroDTO(hero, powerStats);
    }


    public HeroDTO updateById(UUID id, HeroDTO heroDTO) {
        var powerStatsToUpdate = assembler.toPowerStatsDomain(heroDTO);
        powerStatsToUpdate.setId(findHero.findById(id).getPowerStatsId());
        var updatedPowerStats = powerStatsFacade.updatePowerStats(powerStatsToUpdate);


        var heroToUpdate = assembler.toHeroDomain(heroDTO, updatedPowerStats.getId());
        heroToUpdate.setId(id);
        var updatedHero = updateHero.updateHero(heroToUpdate);
        return assembler.toHeroDTO(updatedHero, updatedPowerStats);
    }

    public void deleteById(UUID id) {
        var powerStatsId = findHero.findById(id).getPowerStatsId();
        deleteHero.deleteById(id);
        powerStatsFacade.deleteById(powerStatsId);
    }


    public void deleteByName(String name) {
        name = name.toUpperCase();
        var powerStatsId = findHero.findByName(name).getPowerStatsId();
        deleteHero.deleteByName(name);
        powerStatsFacade.deleteById(powerStatsId);
    }


    public ComparedHeroesDTO compareHeroes(String firstHero, String secondHero) {
        var comparedHeroes = compareHeroes.compareHeroes(firstHero.toUpperCase(), secondHero.toUpperCase());
        return assembler.toComparedHeroesDTO(comparedHeroes);
    }


}
