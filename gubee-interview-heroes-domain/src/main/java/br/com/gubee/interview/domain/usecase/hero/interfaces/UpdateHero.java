package br.com.gubee.interview.domain.usecase.hero.interfaces;


import br.com.gubee.interview.domain.model.hero.Hero;

import java.util.UUID;

public interface UpdateHero {

    Hero updateHero(Hero hero);
}
