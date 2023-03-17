package br.com.gubee.interview.core.features.usecase.hero.interfaces;

import br.com.gubee.interview.model.Hero;

import java.util.UUID;

public interface UpdateHero {

    Hero updateById(UUID id, Hero hero);
}
