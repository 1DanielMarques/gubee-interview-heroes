package br.com.gubee.interview.resource.facade;

import br.com.gubee.interview.domain.model.hero.ComparedHeroes;
import br.com.gubee.interview.domain.model.hero.Hero;
import br.com.gubee.interview.domain.model.powerstats.PowerStats;
import br.com.gubee.interview.domain.usecase.hero.interfaces.*;
import br.com.gubee.interview.resource.assembler.Assembler;
import br.com.gubee.interview.resource.dto.HeroDTO;
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

    public HeroDTO findById(UUID id) {
        var hero = findHero.findById(id);
        var powerStats = powerStatsFacade.findById(hero.getPowerStatsId());
        return assembler.toHeroDTO(hero, powerStats);
    }


    public HeroDTO findByName(String name) {
        var hero = findHero.findByName(name);
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
        var nameUpperCase = name;
        var powerStatsId = findHero.findByName(nameUpperCase).getPowerStatsId();
        deleteHero.deleteByName(nameUpperCase);
        powerStatsFacade.deleteById(powerStatsId);
    }


    public ComparedHeroes compareHeroes(String firstHero, String secondHero) {
        return compareHeroes.compareHeroes(firstHero, secondHero);
    }


}
