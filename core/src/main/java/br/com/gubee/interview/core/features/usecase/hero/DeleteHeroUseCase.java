package br.com.gubee.interview.core.features.usecase.hero;

import br.com.gubee.interview.core.exception.HeroByIdNotFoundException;
import br.com.gubee.interview.core.exception.HeroByNameNotFoundException;
import br.com.gubee.interview.core.features.hero.HeroRepository;
import br.com.gubee.interview.core.features.usecase.hero.interfaces.DeleteHero;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.UUID;

@RequiredArgsConstructor
public class DeleteHeroUseCase implements DeleteHero {

    private final HeroRepository heroRepository;

    @Override
    public void deleteById(UUID id) {
        try {
            heroRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new HeroByIdNotFoundException(id);
        }
    }

    @Override
    public void deleteByName(String name) {
        try {
            heroRepository.deleteByName(name);
        } catch (EmptyResultDataAccessException e) {
            throw new HeroByNameNotFoundException(name);
        }

    }

}
