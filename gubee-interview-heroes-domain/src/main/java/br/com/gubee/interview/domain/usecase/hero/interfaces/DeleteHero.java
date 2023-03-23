package br.com.gubee.interview.domain.usecase.hero.interfaces;

import java.util.UUID;

public interface DeleteHero {

    void deleteById(UUID id);

    void deleteByName(String name);
}
