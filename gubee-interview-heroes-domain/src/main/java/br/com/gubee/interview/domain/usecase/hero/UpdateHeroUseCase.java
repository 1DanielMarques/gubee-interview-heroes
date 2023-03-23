package br.com.gubee.interview.domain.usecase.hero;

import br.com.gubee.interview.domain.hero.Hero;
import br.com.gubee.interview.domain.repository.HeroRepository;
import br.com.gubee.interview.domain.usecase.hero.interfaces.UpdateHero;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class UpdateHeroUseCase implements UpdateHero {
    private final HeroRepository heroRepository;

    @Override
    public Hero updateById(UUID id, Hero hero) {
            hero.setName(hero.getName().toUpperCase());
            hero.setId(id);
            return heroRepository.updateHero(hero);
    }
}
