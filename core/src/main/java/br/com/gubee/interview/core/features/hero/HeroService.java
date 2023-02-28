package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.features.powerstats.PowerStatsService;
import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.request.HeroRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HeroService {

    private final HeroRepository heroRepository;
    private final PowerStatsService powerStatsService;
    private final Assembler assembler;


    @Transactional
    public UUID create(HeroRequest heroRequest) {
        final UUID powerStatsId = powerStatsService.create(assembler.fromRequestToPowerStats(heroRequest));
        return heroRepository.create(new Hero(heroRequest, powerStatsId));
    }

    @Transactional
    public UUID cleaningDBAndCreating(HeroRequest heroRequest) {
        final UUID powerStatsId = powerStatsService.create(assembler.fromRequestToPowerStats(heroRequest));
        return heroRepository.cleaningDBAndCreating(new Hero(heroRequest, powerStatsId));
    }

    public List<Hero> findAll() {
        return heroRepository.findAll();
    }
    public HeroRequest findById(UUID id){
        return heroRepository.findById(id);
    }
    public HeroRequest findByName(String name){
        return heroRepository.findByName(name);
    }
}
