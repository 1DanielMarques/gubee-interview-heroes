package br.com.gubee.interview.core.features.usecase.hero.interfaces;

import br.com.gubee.interview.model.ComparedHeroes;

public interface CompareHeroes {

    ComparedHeroes compareHeroes(String firstHeroName, String secondHeroName);
}
