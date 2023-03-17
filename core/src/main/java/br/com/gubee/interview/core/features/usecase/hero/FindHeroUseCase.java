package br.com.gubee.interview.core.features.usecase.hero;

import br.com.gubee.interview.core.exception.HeroByIdNotFoundException;
import br.com.gubee.interview.core.exception.HeroByNameNotFoundException;
import br.com.gubee.interview.core.exception.ResourceNotFoundException;
import br.com.gubee.interview.core.features.hero.HeroRepository;
import br.com.gubee.interview.core.features.usecase.hero.interfaces.FindHero;
import br.com.gubee.interview.model.Hero;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class FindHeroUseCase implements FindHero {

    private final HeroRepository heroRepository;

    @Override
    public List<Hero> findAll() {
        return heroRepository.findAll();
    }

    @Override
    public Hero findById(UUID id) {
        try {
            return heroRepository.findById(id);
        } catch (ResourceNotFoundException e) {
            throw new HeroByIdNotFoundException(id);
        }
    }

    @Override
    public Hero findByName(String name) {
        try {
            return heroRepository.findByName(name);
        } catch (ResourceNotFoundException e) {
            throw new HeroByNameNotFoundException(name);
        }

    }

}
