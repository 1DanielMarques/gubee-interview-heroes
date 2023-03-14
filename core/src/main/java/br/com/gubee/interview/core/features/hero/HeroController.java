package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.model.request.ComparedHeroes;
import br.com.gubee.interview.model.request.HeroRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @GetMapping
    public ResponseEntity<List<HeroRequest>> findAll() {
        List<HeroRequest> heroList = heroService.findAll();
        return ResponseEntity.ok().body(heroList);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<HeroRequest> create(@Validated @RequestBody HeroRequest heroRequest) {
        final HeroRequest hero = heroService.create(heroRequest);
        return created(URI.create(format("/api/v1/heroes/%s", hero.getId()))).body(hero);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<HeroRequest> findById(@PathVariable(value = "id") UUID id) {
        return ResponseEntity.ok().body(heroService.findById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<HeroRequest> findByName(@PathVariable(value = "name") String name) {
        return ResponseEntity.ok().body(heroService.findByName(name));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateById(@PathVariable(value = "id") UUID id, @RequestBody HeroRequest heroRequest) {
        return ResponseEntity.status(heroService.updateById(id, heroRequest)).build();
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "id") UUID id) {
        heroService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/compare/{firstHero}/with/{secondHero}")
    public ResponseEntity<ComparedHeroes> compareHeroes(@PathVariable(value = "firstHero") String firstHero, @PathVariable(value = "secondHero") String secondHero) {
        return ResponseEntity.ok().body(heroService.compareHeroes(firstHero, secondHero));
    }


}
