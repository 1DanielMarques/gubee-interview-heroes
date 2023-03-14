package br.com.gubee.interview.core.features.usecase;

import br.com.gubee.interview.core.features.hero.HeroRepository;
import br.com.gubee.interview.core.features.hero.HeroRepositoryImpl;
import br.com.gubee.interview.core.features.usecase.interfaces.FindHero;
import br.com.gubee.interview.model.request.HeroRequest;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class FindHeroUseCase implements FindHero {

    private final HeroRepository heroRepository;

    @Override
    public List<HeroRequest> findAll() {
        return heroRepository.findAll();
    }

    @Override
    public HeroRequest findById(UUID id) {
        return heroRepository.findById(id);
    }

    @Override
    public HeroRequest findByName(String name) {
        return heroRepository.findByName(name);
    }

    @Override
    public UUID getHeroIdByName(String name) {
        return heroRepository.getHeroIdByName(name);
    }
}
