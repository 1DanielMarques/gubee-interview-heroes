package br.com.gubee.interview.core.features.usecase.hero.interfaces;

import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.dto.HeroDTO;

import java.util.UUID;

public interface CreateHero {

    Hero create(Hero hero);

}
