package br.com.gubee.interview.domain.usecase.hero;

import br.com.gubee.interview.domain.exceptions.HeroByIdNotFoundException;
import br.com.gubee.interview.domain.exceptions.ResourceNotFoundException;
import br.com.gubee.interview.domain.model.hero.Hero;
import br.com.gubee.interview.domain.repository.HeroRepository;
import br.com.gubee.interview.domain.usecase.hero.interfaces.UpdateHero;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateHeroUseCase implements UpdateHero {
    private final HeroRepository heroRepository;

    @Override
    public Hero updateHero(Hero hero) {
        hero.setName(hero.getName().toUpperCase());
        try {
            return heroRepository.updateHero(hero);
        } catch (ResourceNotFoundException e) {
            throw new HeroByIdNotFoundException(hero.getId());
        }

    }
}
