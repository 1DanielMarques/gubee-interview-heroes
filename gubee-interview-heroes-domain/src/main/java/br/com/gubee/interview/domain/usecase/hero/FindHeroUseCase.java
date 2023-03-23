package br.com.gubee.interview.domain.usecase.hero;

import br.com.gubee.interview.domain.hero.Hero;
import br.com.gubee.interview.domain.repository.HeroRepository;
import br.com.gubee.interview.domain.usecase.hero.interfaces.FindHero;
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
        return heroRepository.findById(id);
    }

    @Override
    public Hero findByName(String name) {
        return heroRepository.findByName(name.toUpperCase());
    }

}
