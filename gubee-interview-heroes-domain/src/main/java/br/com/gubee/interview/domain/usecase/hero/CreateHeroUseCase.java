package br.com.gubee.interview.domain.usecase.hero;

import br.com.gubee.interview.domain.exceptions.HeroAlreadyExistException;
import br.com.gubee.interview.domain.model.hero.Hero;
import br.com.gubee.interview.domain.spi.hero.HeroRepository;
import br.com.gubee.interview.domain.api.hero.CreateHero;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateHeroUseCase implements CreateHero {

    private final HeroRepository heroRepository;

    @Override
    public Hero create(Hero hero) {
        if (heroRepository.exist(hero.getName()))
            throw new HeroAlreadyExistException(hero.getName());
        return heroRepository.create(hero);
    }
}
