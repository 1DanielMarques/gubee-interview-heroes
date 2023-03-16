package br.com.gubee.interview.core.features.usecase.hero;

import br.com.gubee.interview.core.features.hero.HeroRepository;
import br.com.gubee.interview.core.features.usecase.hero.interfaces.UpdateHero;
import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.entities.HeroEntity;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class UpdateHeroUseCase implements UpdateHero {
    private final HeroRepository heroRepository;

    @Override
    public Hero updateById(UUID id, Hero hero) {
       return heroRepository.updateById(id, HeroEntity.fromHero(hero)).toHero();
    }
}
