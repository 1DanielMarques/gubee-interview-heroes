package br.com.gubee.interview.domain.usecase.hero.interfaces;


import br.com.gubee.interview.domain.hero.ComparedHeroes;

public interface CompareHeroes {

    ComparedHeroes compareHeroes(String firstHeroName, String secondHeroName);
}
