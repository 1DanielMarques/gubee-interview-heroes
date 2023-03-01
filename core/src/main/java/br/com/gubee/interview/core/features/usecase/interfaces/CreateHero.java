package br.com.gubee.interview.core.features.usecase.interfaces;

import br.com.gubee.interview.model.request.HeroRequest;

import java.util.UUID;

public interface CreateHero {

    UUID create(HeroRequest heroRequest, UUID powerStatsId);

}
