package br.com.gubee.interview.core.features.usecase.interfaces;

import br.com.gubee.interview.model.request.HeroRequest;

import java.util.List;
import java.util.UUID;

public interface FindHero {

    List<HeroRequest> findAll();

    HeroRequest findById(UUID id);

    HeroRequest findByName(String name);
}
