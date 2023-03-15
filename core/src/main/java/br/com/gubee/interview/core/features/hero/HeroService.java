package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.model.request.ComparedHeroes;
import br.com.gubee.interview.model.request.HeroRequest;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.UUID;

public interface HeroService {

    HeroRequest create(HeroRequest heroRequest);
    List<HeroRequest> findAll();
    HeroRequest findById(UUID id);
    HeroRequest findByName(String name);
    HttpStatus updateById(UUID id, HeroRequest heroRequest);
    void deleteById(UUID id);
    ComparedHeroes compareHeroes(String firstHero, String secondHero);
    void deleteByName(String name);

    UUID getHeroIdByName(String name);
}
