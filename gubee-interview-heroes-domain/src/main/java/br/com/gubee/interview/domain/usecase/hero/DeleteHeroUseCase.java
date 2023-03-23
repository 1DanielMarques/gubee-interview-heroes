package br.com.gubee.interview.domain.usecase.hero;

import br.com.gubee.interview.domain.repository.HeroRepository;
import br.com.gubee.interview.domain.usecase.hero.interfaces.DeleteHero;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class DeleteHeroUseCase implements DeleteHero {

    private final HeroRepository heroRepository;

    @Override
    public void deleteById(UUID id) {
            heroRepository.deleteById(id);
    }

    @Override
    public void deleteByName(String name) {
            heroRepository.deleteByName(name.toUpperCase());
    }

}
