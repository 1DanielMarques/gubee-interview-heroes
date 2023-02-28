package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.features.powerstats.PowerStatsService;
import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.request.CreateHeroRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static java.lang.String.format;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.created;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/heroes", produces = APPLICATION_JSON_VALUE)
public class HeroController {

    private final HeroService heroService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@Validated
                                       @RequestBody CreateHeroRequest createHeroRequest) {
        final UUID heroId = heroService.create(createHeroRequest);
        return created(URI.create(format("/api/v1/heroes/%s", heroId))).build();
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, value = "/clean")
    public ResponseEntity<Void> createWithEmptyDB(@Validated
                                       @RequestBody CreateHeroRequest createHeroRequest) {
        final UUID heroId = heroService.cleaningDBAndCreating(createHeroRequest);
        return created(URI.create(format("/api/v1/heroes/%s", heroId))).build();
    }

    @GetMapping
    public ResponseEntity<List<Hero>> getHeroes() {
        List<Hero> heroList = heroService.findAll();
        return ResponseEntity.ok().body(heroList);
    }




}
