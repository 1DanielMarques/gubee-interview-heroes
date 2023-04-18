package br.com.gubee.interview.domain.api.hero;

import java.util.UUID;

public interface DeleteHero {

    void deleteById(UUID id);

    void deleteByName(String name);
}
