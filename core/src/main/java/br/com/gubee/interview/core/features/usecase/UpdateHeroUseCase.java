package br.com.gubee.interview.core.features.usecase;

import br.com.gubee.interview.core.features.hero.HeroRepository;
import br.com.gubee.interview.core.features.usecase.interfaces.UpdateHero;
import br.com.gubee.interview.model.request.HeroRequest;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class UpdateHeroUseCase implements UpdateHero {
    private final HeroRepository heroRepository;

    @Override
    public void updateById(UUID id, HeroRequest heroRequest) {
        heroRepository.updateById(id, heroRequest);
    }
}
