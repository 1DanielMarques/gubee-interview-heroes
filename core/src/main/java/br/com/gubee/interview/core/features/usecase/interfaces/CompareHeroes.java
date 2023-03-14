package br.com.gubee.interview.core.features.usecase.interfaces;

import br.com.gubee.interview.model.request.ComparedHeroes;

public interface CompareHeroes {

    ComparedHeroes compareHeroes(String firstHeroName, String secondHeroName);
}
