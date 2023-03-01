package br.com.gubee.interview.core.features.usecase;

import br.com.gubee.interview.core.features.hero.HeroRepository;
import br.com.gubee.interview.core.features.usecase.interfaces.CompareHeroes;
import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.model.request.ComparedHeroes;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
public class CompareHeroesUseCase implements CompareHeroes {

    private final HeroRepository heroRepository;

    @Override
    public ComparedHeroes compareHeroes(String name_1, String name_2) {
        ComparedHeroes comparedHeroes = new ComparedHeroes();

        UUID id_1 = heroRepository.getHeroIdByName(name_1);
        comparedHeroes.setId_1(id_1);
        UUID id_2 = heroRepository.getHeroIdByName(name_2);
        comparedHeroes.setId_2(id_2);

        Map<UUID, PowerStats> heroesPowerStats = heroRepository.compareHeroes(id_1, id_2);
        PowerStats hero_1 = heroesPowerStats.get(id_1);
        PowerStats hero_2 = heroesPowerStats.get(id_2);
        compareHeroesAttributes(comparedHeroes, hero_1, hero_2);
        return comparedHeroes;
    }

    private void compareHeroesAttributes(ComparedHeroes comparedHeroes, PowerStats hero_1, PowerStats hero_2) {
        comparedHeroes.setStrength_1((hero_1.getStrength() >= hero_2.getStrength()) ? hero_1.getStrength() : hero_1.getStrength() * -1);
        comparedHeroes.setAgility_1((hero_1.getAgility() >= hero_2.getAgility()) ? hero_1.getAgility() : hero_1.getAgility() * -1);
        comparedHeroes.setDexterity_1((hero_1.getDexterity() >= hero_2.getDexterity()) ? hero_1.getDexterity() : hero_1.getDexterity() * -1);
        comparedHeroes.setIntelligence_1((hero_1.getIntelligence() >= hero_2.getIntelligence()) ? hero_1.getIntelligence() : hero_1.getIntelligence() * -1);

        comparedHeroes.setStrength_2((hero_2.getStrength() >= hero_1.getStrength()) ? hero_2.getStrength() : hero_2.getStrength() * -1);
        comparedHeroes.setAgility_2((hero_2.getAgility() >= hero_1.getAgility()) ? hero_2.getAgility() : hero_2.getAgility() * -1);
        comparedHeroes.setDexterity_2((hero_2.getDexterity() >= hero_1.getDexterity()) ? hero_2.getDexterity() : hero_2.getDexterity() * -1);
        comparedHeroes.setIntelligence_2((hero_2.getIntelligence() >= hero_1.getIntelligence()) ? hero_2.getIntelligence() : hero_2.getIntelligence() * -1);
    }
}
