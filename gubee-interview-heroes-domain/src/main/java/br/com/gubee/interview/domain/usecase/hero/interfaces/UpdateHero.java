package br.com.gubee.interview.domain.usecase.hero.interfaces;


import br.com.gubee.interview.domain.hero.Hero;

import java.util.UUID;

public interface UpdateHero {

    Hero updateById(UUID id, Hero hero);
}
