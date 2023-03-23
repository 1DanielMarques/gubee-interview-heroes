package br.com.gubee.interview.domain.usecase.hero;

import br.com.gubee.interview.domain.hero.Hero;
import br.com.gubee.interview.domain.repository.HeroRepository;
import br.com.gubee.interview.domain.usecase.hero.interfaces.CreateHero;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateHeroUseCase implements CreateHero {

    private final HeroRepository heroRepository;

    @Override
    public Hero create(Hero hero) {
        hero.setName(hero.getName().toUpperCase());
        /*if (heroRepository.exist(hero.getName()))
            throw new HeroAlreadyExistException(hero.getName());*/
        return heroRepository.create(hero);
    }
}
