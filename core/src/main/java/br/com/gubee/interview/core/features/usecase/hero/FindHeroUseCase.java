package br.com.gubee.interview.core.features.usecase.hero;

import br.com.gubee.interview.core.features.hero.HeroRepository;
import br.com.gubee.interview.core.features.usecase.hero.interfaces.FindHero;
import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.dto.HeroDTO;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class FindHeroUseCase implements FindHero {

    private final HeroRepository heroRepository;

    @Override
    public List<Hero> findAll() {
        return heroRepository.findAll().stream().map(entity -> entity.toHero()).toList();
    }

    @Override
    public Hero findById(UUID id) {
        return heroRepository.findById(id).toHero();
    }

    @Override
    public HeroDTO findByName(String name) {
        return heroRepository.findByName(name);
    }

    @Override
    public UUID getHeroIdByName(String name) {
        return heroRepository.getHeroIdByName(name);
    }
}
