package br.com.gubee.interview.core.features.usecase.hero;

import br.com.gubee.interview.core.exception.HeroByIdNotFoundException;
import br.com.gubee.interview.core.exception.ResourceNotFoundException;
import br.com.gubee.interview.core.features.hero.HeroRepository;
import br.com.gubee.interview.core.features.usecase.hero.interfaces.UpdateHero;
import br.com.gubee.interview.model.Hero;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
public class UpdateHeroUseCase implements UpdateHero {
    private final HeroRepository heroRepository;

    @Transactional
    @Override
    public Hero updateById(UUID id, Hero hero) {
        try {
            hero.setName(hero.getName().toUpperCase());
            return heroRepository.updateById(id, hero);
        } catch (ResourceNotFoundException e) {
            throw new HeroByIdNotFoundException(id);
        }
    }
}
