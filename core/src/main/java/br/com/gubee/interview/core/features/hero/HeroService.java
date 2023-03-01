package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.exception.HeroByIdNotFound;
import br.com.gubee.interview.core.exception.HeroByNameNotFound;
import br.com.gubee.interview.core.features.powerstats.PowerStatsService;
import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.model.request.ComparedHeroes;
import br.com.gubee.interview.model.request.HeroRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HeroService {

    private final HeroRepository heroRepository;
    private final PowerStatsService powerStatsService;
    private final Assembler assembler;


    @Transactional
    public UUID create(HeroRequest heroRequest) {
        final UUID powerStatsId = powerStatsService.create(assembler.fromRequestToPowerStats(heroRequest));
        return heroRepository.create(new Hero(heroRequest, powerStatsId));
    }

    @Transactional
    public UUID cleaningDBAndCreating(HeroRequest heroRequest) {
        final UUID powerStatsId = powerStatsService.create(assembler.fromRequestToPowerStats(heroRequest));
        return heroRepository.cleaningDBAndCreating(new Hero(heroRequest, powerStatsId));
    }

    public List<Hero> findAll() {
        return heroRepository.findAll();
    }

    public HeroRequest findById(UUID id) {
        try {
            return heroRepository.findById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new HeroByIdNotFound(id);
        }
    }

    public HeroRequest findByName(String name) {
        try {
            return heroRepository.findByName(name);
        } catch (EmptyResultDataAccessException e) {
            throw new HeroByNameNotFound(name);
        }
    }

    public HttpStatus updateById(UUID id, HeroRequest heroRequest) {
        try {
            heroRepository.updateById(id, heroRequest);
            return HttpStatus.valueOf(200);
        } catch (EmptyResultDataAccessException e) {
            throw new HeroByIdNotFound(id);
        }
    }

    public void deleteById(UUID id) {
        try {
            heroRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new HeroByIdNotFound(id);
        }
    }

    public ComparedHeroes compareHeroes(String name_1, String name_2) {
        UUID id_1 = heroRepository.getHeroIdByName(name_1);
        UUID id_2 = heroRepository.getHeroIdByName(name_2);
        Map<UUID, PowerStats> heroesAttributes = heroRepository.compareHeroes(id_1,id_2);
        PowerStats hero_1 = heroesAttributes.get(id_1);
        PowerStats hero_2 = heroesAttributes.get(id_2);
        ComparedHeroes comparedHeroes = new ComparedHeroes();

        comparedHeroes.setId_1(id_1);
        comparedHeroes.setStrength_1((hero_1.getStrength() >= hero_2.getStrength()) ? hero_1.getStrength() : hero_1.getStrength() * -1);
        comparedHeroes.setAgility_1((hero_1.getAgility() >= hero_2.getAgility()) ? hero_1.getAgility() : hero_1.getAgility() * -1);
        comparedHeroes.setDexterity_1((hero_1.getDexterity() >= hero_2.getDexterity()) ? hero_1.getDexterity() : hero_1.getDexterity() * -1);
        comparedHeroes.setIntelligence_1((hero_1.getIntelligence() >= hero_2.getIntelligence()) ? hero_1.getIntelligence() : hero_1.getIntelligence() * -1);

        comparedHeroes.setId_2(id_2);
        comparedHeroes.setStrength_2((hero_2.getStrength() >= hero_1.getStrength()) ? hero_2.getStrength() : hero_2.getStrength() * -1);
        comparedHeroes.setAgility_2((hero_2.getAgility() >= hero_1.getAgility()) ? hero_2.getAgility() : hero_2.getAgility() * -1);
        comparedHeroes.setDexterity_2((hero_2.getDexterity() >= hero_1.getDexterity()) ? hero_2.getDexterity() : hero_2.getDexterity() * -1);
        comparedHeroes.setIntelligence_2((hero_2.getIntelligence() >= hero_1.getIntelligence()) ? hero_2.getIntelligence() : hero_2.getIntelligence() * -1);
        return comparedHeroes;
    }

}
