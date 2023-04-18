package br.com.gubee.interview.domain.usecase.hero;

import br.com.gubee.interview.domain.model.hero.Hero;
import br.com.gubee.interview.domain.spi.hero.HeroRepository;
import br.com.gubee.interview.domain.api.hero.UpdateHero;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateHeroUseCase implements UpdateHero {
    private final HeroRepository heroRepository;

    @Override
    public Hero updateHero(Hero hero) {
        return heroRepository.updateHero(hero);
    }
}
