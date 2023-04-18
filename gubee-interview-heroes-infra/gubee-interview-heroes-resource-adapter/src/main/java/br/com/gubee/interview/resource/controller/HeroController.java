package br.com.gubee.interview.resource.controller;

import br.com.gubee.interview.domain.model.hero.ComparedHeroes;
import br.com.gubee.interview.resource.dto.HeroDTO;
import br.com.gubee.interview.resource.facade.HeroFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.util.List;
import java.util.UUID;

import static java.lang.String.format;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/heroes", produces = MediaType.APPLICATION_JSON_VALUE)
public class HeroController {

    private final HeroFacade heroFacade;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HeroDTO> create(@Validated @RequestBody HeroDTO heroToCreate) {
        var heroDTO = heroFacade.create(heroToCreate);
        return ResponseEntity.created(URI.create(format("/api/v1/heroes/%s", heroDTO.getId()))).body(heroDTO);
    }

    @GetMapping
    public ResponseEntity<List<HeroDTO>> findAll() {
        List<HeroDTO> heroDTOList = heroFacade.findAll();
        return ResponseEntity.ok().body(heroDTOList);
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<HeroDTO> findById(@PathVariable(value = "id") UUID id) {
        var heroDTO = heroFacade.findById(id);
        return ResponseEntity.ok().body(heroDTO);
    }


    @GetMapping("/name/{name}")
    public ResponseEntity<HeroDTO> findByName(@PathVariable(value = "name") String name) {
        var heroDTO = heroFacade.findByName(name);
        return ResponseEntity.ok().body(heroDTO);
    }


    @PutMapping("/id/{id}")
    public ResponseEntity<HeroDTO> updateById(@PathVariable(value = "id") UUID id,@Validated @RequestBody HeroDTO heroToUpdate) {
        var heroDTO = heroFacade.updateById(id, heroToUpdate);
        return ResponseEntity.ok().body(heroDTO);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "id") UUID id) {
        heroFacade.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/name/{name}")
    public ResponseEntity<Void> deleteByName(@PathVariable(value = "name") String name) {
        heroFacade.deleteByName(name);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/compare/{firstHero}/with/{secondHero}")
    public ResponseEntity<ComparedHeroes> compareHeroes(@PathVariable(value = "firstHero") String firstHero, @PathVariable(value = "secondHero") String secondHero) {
        return ResponseEntity.ok().body(heroFacade.compareHeroes(firstHero, secondHero));
    }


}
