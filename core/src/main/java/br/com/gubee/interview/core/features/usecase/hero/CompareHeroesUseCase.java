package br.com.gubee.interview.core.features.usecase.hero;

import br.com.gubee.interview.core.exception.HeroByNameNotFoundException;
import br.com.gubee.interview.core.exception.ResourceNotFoundException;
import br.com.gubee.interview.core.features.hero.HeroRepository;
import br.com.gubee.interview.core.features.powerstats.PowerStatsRepository;
import br.com.gubee.interview.core.features.usecase.hero.interfaces.CompareHeroes;
import br.com.gubee.interview.model.ComparedHeroes;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CompareHeroesUseCase implements CompareHeroes {

    private final HeroRepository heroRepository;
    private final PowerStatsRepository powerStatsRepository;


    @Override
    public ComparedHeroes compareHeroes(String firstHeroName, String secondHeroName) {
        try {
            ComparedHeroes comparedHeroes = new ComparedHeroes();

            var firstHero = heroRepository.findByName(firstHeroName.toUpperCase());
            var secondHero = heroRepository.findByName(secondHeroName.toUpperCase());

            var firstPowerStats = powerStatsRepository.findById(firstHero.getPowerStatsId());
            var secondPowerStats = powerStatsRepository.findById(secondHero.getPowerStatsId());


            comparedHeroes.setFirstId(firstHero.getId());
            comparedHeroes.setFirstStrength((firstPowerStats.getStrength() >= secondPowerStats.getStrength()) ? firstPowerStats.getStrength() : firstPowerStats.getStrength() * -1);
            comparedHeroes.setFirstAgility((firstPowerStats.getAgility() >= secondPowerStats.getAgility()) ? firstPowerStats.getAgility() : firstPowerStats.getAgility() * -1);
            comparedHeroes.setFirstDexterity((firstPowerStats.getDexterity() >= secondPowerStats.getDexterity()) ? firstPowerStats.getDexterity() : firstPowerStats.getDexterity() * -1);
            comparedHeroes.setFirstIntelligence((firstPowerStats.getIntelligence() >= secondPowerStats.getIntelligence()) ? firstPowerStats.getIntelligence() : firstPowerStats.getIntelligence() * -1);

            comparedHeroes.setSecondId(secondHero.getId());
            comparedHeroes.setSecondStrength((secondPowerStats.getStrength() >= firstPowerStats.getStrength()) ? secondPowerStats.getStrength() : secondPowerStats.getStrength() * -1);
            comparedHeroes.setSecondAgility((secondPowerStats.getAgility() >= firstPowerStats.getAgility()) ? secondPowerStats.getAgility() : secondPowerStats.getAgility() * -1);
            comparedHeroes.setSecondDexterity((secondPowerStats.getDexterity() >= firstPowerStats.getDexterity()) ? secondPowerStats.getDexterity() : secondPowerStats.getDexterity() * -1);
            comparedHeroes.setSecondIntelligence((secondPowerStats.getIntelligence() >= firstPowerStats.getIntelligence()) ? secondPowerStats.getIntelligence() : secondPowerStats.getIntelligence() * -1);
            return comparedHeroes;
        } catch (ResourceNotFoundException e) {
            // Is it clean?
            if (!heroRepository.exist(firstHeroName.toUpperCase())) {
                throw new HeroByNameNotFoundException(firstHeroName);
            } else {
                throw new HeroByNameNotFoundException(secondHeroName);
            }
        }
    }


}
