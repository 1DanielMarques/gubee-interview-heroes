package br.com.gubee.interview.domain.api.hero;


import br.com.gubee.interview.domain.model.hero.ComparedHeroes;

public interface CompareHeroes {

    ComparedHeroes compareHeroes(String firstHeroName, String secondHeroName);
}
