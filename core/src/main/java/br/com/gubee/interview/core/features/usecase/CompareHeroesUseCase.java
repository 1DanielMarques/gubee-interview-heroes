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
    public ComparedHeroes compareHeroes(String firstHero, String secondHero) {
        ComparedHeroes comparedHeroes = new ComparedHeroes();

        UUID FirstHeroId = heroRepository.getHeroIdByName(firstHero);
        comparedHeroes.setFirstId(FirstHeroId);
        UUID SecondHeroId = heroRepository.getHeroIdByName(secondHero);
        comparedHeroes.setSecondId(SecondHeroId);

        Map<UUID, PowerStats> heroesPowerStats = heroRepository.compareHeroes(FirstHeroId, SecondHeroId);
        PowerStats firstHeroPowerStats = heroesPowerStats.get(FirstHeroId);
        PowerStats secondHeroPowerStats = heroesPowerStats.get(SecondHeroId);
        compareHeroesAttributes(comparedHeroes, firstHeroPowerStats, secondHeroPowerStats);
        return comparedHeroes;
    }

    private void compareHeroesAttributes(ComparedHeroes comparedHeroes, PowerStats firstHeroPowerStats, PowerStats secondHeroPowerStats) {
        comparedHeroes.setFirstStrength((firstHeroPowerStats.getStrength() >= secondHeroPowerStats.getStrength()) ? firstHeroPowerStats.getStrength() : firstHeroPowerStats.getStrength() * -1);
        comparedHeroes.setFirstAgility((firstHeroPowerStats.getAgility() >= secondHeroPowerStats.getAgility()) ? firstHeroPowerStats.getAgility() : firstHeroPowerStats.getAgility() * -1);
        comparedHeroes.setFirstDexterity((firstHeroPowerStats.getDexterity() >= secondHeroPowerStats.getDexterity()) ? firstHeroPowerStats.getDexterity() : firstHeroPowerStats.getDexterity() * -1);
        comparedHeroes.setFirstIntelligence((firstHeroPowerStats.getIntelligence() >= secondHeroPowerStats.getIntelligence()) ? firstHeroPowerStats.getIntelligence() : firstHeroPowerStats.getIntelligence() * -1);

        comparedHeroes.setSecondStrength((secondHeroPowerStats.getStrength() >= firstHeroPowerStats.getStrength()) ? secondHeroPowerStats.getStrength() : secondHeroPowerStats.getStrength() * -1);
        comparedHeroes.setSecondAgility((secondHeroPowerStats.getAgility() >= firstHeroPowerStats.getAgility()) ? secondHeroPowerStats.getAgility() : secondHeroPowerStats.getAgility() * -1);
        comparedHeroes.setSecondDexterity((secondHeroPowerStats.getDexterity() >= firstHeroPowerStats.getDexterity()) ? secondHeroPowerStats.getDexterity() : secondHeroPowerStats.getDexterity() * -1);
        comparedHeroes.setSecondIntelligence((secondHeroPowerStats.getIntelligence() >= firstHeroPowerStats.getIntelligence()) ? secondHeroPowerStats.getIntelligence() : secondHeroPowerStats.getIntelligence() * -1);
    }
}
