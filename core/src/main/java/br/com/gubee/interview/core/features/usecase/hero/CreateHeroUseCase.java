package br.com.gubee.interview.core.features.usecase.hero;

import br.com.gubee.interview.core.features.hero.HeroRepository;
import br.com.gubee.interview.core.features.usecase.hero.interfaces.CreateHero;
import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.entities.HeroEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateHeroUseCase implements CreateHero {

    private final HeroRepository heroRepository;

    @Override
    public Hero create(Hero hero) {
        //verificar ID nulo
        // verificar se já existe
        return heroRepository.create(HeroEntity.fromHero(hero)).toHero();
    }
}
