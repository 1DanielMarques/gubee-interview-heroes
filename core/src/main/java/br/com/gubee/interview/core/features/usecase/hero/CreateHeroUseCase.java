package br.com.gubee.interview.core.features.usecase.hero;

import br.com.gubee.interview.core.exception.HeroAlreadyExistException;
import br.com.gubee.interview.core.features.hero.HeroRepository;
import br.com.gubee.interview.core.features.usecase.hero.interfaces.CreateHero;
import br.com.gubee.interview.model.Hero;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class CreateHeroUseCase implements CreateHero {

    private final HeroRepository heroRepository;

    @Transactional
    @Override
    public Hero create(Hero hero) {
        hero.setName(hero.getName().toUpperCase());
        if (heroRepository.exist(hero.getName()))
            throw new HeroAlreadyExistException(hero.getName());
        return heroRepository.create(hero);
    }
}
