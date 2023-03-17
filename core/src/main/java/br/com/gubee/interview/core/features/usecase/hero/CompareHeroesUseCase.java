package br.com.gubee.interview.core.features.usecase.hero;

import br.com.gubee.interview.core.features.hero.HeroRepository;
import br.com.gubee.interview.core.features.usecase.hero.interfaces.CompareHeroes;
import br.com.gubee.interview.model.ComparedHeroes;
import br.com.gubee.interview.model.PowerStats;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
public class CompareHeroesUseCase implements CompareHeroes {

    private final HeroRepository heroRepository;


    @Override
    public ComparedHeroes compareHeroes(String firstHeroName, String secondHeroName) {

        /* 1.busca p nome o heroi
            2. busca poderes do heroi
            3. compara os herors
            4. retorna comparedheroes


         */
        ComparedHeroes comparedHeroes = new ComparedHeroes();
        UUID firstHeroId = null;
        UUID secondHeroId = null;

        Map<UUID, PowerStats> heroesPowerStats = heroRepository.compareHeroes(firstHeroId, secondHeroId);

        PowerStats firstHeroPowerStats = heroesPowerStats.get(firstHeroId);
        PowerStats secondHeroPowerStats = heroesPowerStats.get(secondHeroId);

        comparedHeroes.setFirstId(firstHeroId);
        comparedHeroes.setFirstStrength((firstHeroPowerStats.getStrength() >= secondHeroPowerStats.getStrength()) ? firstHeroPowerStats.getStrength() : firstHeroPowerStats.getStrength() * -1);
        comparedHeroes.setFirstAgility((firstHeroPowerStats.getAgility() >= secondHeroPowerStats.getAgility()) ? firstHeroPowerStats.getAgility() : firstHeroPowerStats.getAgility() * -1);
        comparedHeroes.setFirstDexterity((firstHeroPowerStats.getDexterity() >= secondHeroPowerStats.getDexterity()) ? firstHeroPowerStats.getDexterity() : firstHeroPowerStats.getDexterity() * -1);
        comparedHeroes.setFirstIntelligence((firstHeroPowerStats.getIntelligence() >= secondHeroPowerStats.getIntelligence()) ? firstHeroPowerStats.getIntelligence() : firstHeroPowerStats.getIntelligence() * -1);

        comparedHeroes.setSecondId(secondHeroId);
        comparedHeroes.setSecondStrength((secondHeroPowerStats.getStrength() >= firstHeroPowerStats.getStrength()) ? secondHeroPowerStats.getStrength() : secondHeroPowerStats.getStrength() * -1);
        comparedHeroes.setSecondAgility((secondHeroPowerStats.getAgility() >= firstHeroPowerStats.getAgility()) ? secondHeroPowerStats.getAgility() : secondHeroPowerStats.getAgility() * -1);
        comparedHeroes.setSecondDexterity((secondHeroPowerStats.getDexterity() >= firstHeroPowerStats.getDexterity()) ? secondHeroPowerStats.getDexterity() : secondHeroPowerStats.getDexterity() * -1);
        comparedHeroes.setSecondIntelligence((secondHeroPowerStats.getIntelligence() >= firstHeroPowerStats.getIntelligence()) ? secondHeroPowerStats.getIntelligence() : secondHeroPowerStats.getIntelligence() * -1);
        return comparedHeroes;
    }


}
