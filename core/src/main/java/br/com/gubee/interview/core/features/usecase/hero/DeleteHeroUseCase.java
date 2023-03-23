package br.com.gubee.interview.core.features.usecase.hero;

import br.com.gubee.interview.core.exception.HeroByIdNotFoundException;
import br.com.gubee.interview.core.exception.HeroByNameNotFoundException;
import br.com.gubee.interview.core.exception.ResourceNotFoundException;
import br.com.gubee.interview.core.features.hero.HeroRepository;
import br.com.gubee.interview.core.features.usecase.hero.interfaces.DeleteHero;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
public class DeleteHeroUseCase implements DeleteHero {

    private final HeroRepository heroRepository;

    @Transactional
    @Override
    public void deleteById(UUID id) {
            heroRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteByName(String name) {
            heroRepository.deleteByName(name.toUpperCase());
    }

}
