package br.com.gubee.interview.core.features.usecase.hero;

import br.com.gubee.interview.core.features.hero.HeroRepository;
import br.com.gubee.interview.core.features.usecase.hero.interfaces.UpdateHero;
import br.com.gubee.interview.model.dto.HeroDTO;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class UpdateHeroUseCase implements UpdateHero {
    private final HeroRepository heroRepository;

    @Override
    public void updateById(UUID id, HeroDTO heroDTO) {
        heroRepository.updateById(id, heroDTO);
    }
}
