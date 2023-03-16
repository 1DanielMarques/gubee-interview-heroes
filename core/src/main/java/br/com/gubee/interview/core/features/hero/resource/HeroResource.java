package br.com.gubee.interview.core.features.hero.resource;

import br.com.gubee.interview.core.features.hero.resource.facade.HeroFacade;
import br.com.gubee.interview.model.dto.HeroDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static java.lang.String.format;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.created;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/heroes", produces = APPLICATION_JSON_VALUE)
public class HeroResource {

    private final HeroFacade heroFacade;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<HeroDTO> create(@Validated @RequestBody HeroDTO heroDTO) {
        var heroDto = heroFacade.create(heroDTO);
        return created(URI.create(format("/api/v1/heroes/%s", heroDto.getId()))).body(heroDTO);
    }
/*
    @GetMapping
    public ResponseEntity<List<HeroRequest>> findAll() {
        List<Hero> heroList = heroService.findAll();
        List<PowerStats> powerStatsList = powerStatsService.findAll();
        List<HeroRequest> heroRequestList = assembler.toRequestList(heroList, powerStatsList);
        return ResponseEntity.ok().body(heroRequestList);
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
    } */


}
