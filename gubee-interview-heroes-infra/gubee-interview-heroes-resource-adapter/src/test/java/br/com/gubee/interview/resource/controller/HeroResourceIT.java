package br.com.gubee.interview.resource.controller;

import br.com.gubee.interview.domain.enums.Race;
import br.com.gubee.interview.domain.exceptions.HeroByIdNotFoundException;
import br.com.gubee.interview.domain.exceptions.HeroByNameNotFoundException;
import br.com.gubee.interview.domain.model.hero.Hero;
import br.com.gubee.interview.domain.model.powerstats.PowerStats;
import br.com.gubee.interview.domain.repository.HeroRepository;
import br.com.gubee.interview.domain.repository.PowerStatsRepository;
import br.com.gubee.interview.resource.dto.HeroDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class HeroResourceIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private HeroRepository heroRepository;
    @Autowired
    private PowerStatsRepository powerStatsRepository;

    @Autowired
    ObjectMapper mapper;

    private PowerStats powerStats;
    private Hero hero;

    @BeforeEach
    void up() {
        powerStats = PowerStats.builder()
                .agility(3)
                .dexterity(4)
                .strength(5)
                .intelligence(6)
                .build();
        powerStats = powerStatsRepository.create(powerStats);
        hero = Hero.builder()
                .name("BATMAN")
                .race(Race.HUMAN)
                .powerStatsId(powerStats.getId())
                .build();
        hero = heroRepository.create(hero);
    }

    @AfterEach
    void down() {
        try {
            heroRepository.deleteById(hero.getId());
            powerStatsRepository.deleteById(powerStats.getId());
        } catch (Exception e) {
            System.out.println("Doesn't exist");
        }


    }


    @Test
    void returnListOfHeroes() throws Exception {

        //when
        var result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/heroes"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        //then
        result.andExpect(jsonPath("$[0].name").value("BATMAN"));
    }

    @Test
    void shouldCreateHeroAndReturnIt() throws Exception {
        //given
        final String body = mapper.writeValueAsString(createHeroRequest());

        //when
        final ResultActions resultActions = mockMvc.perform(post("/api/v1/heroes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body));

        //then
        var heroCreated = heroRepository.findByName("SUPERMAN");
        var json = String.format("{ \"id\": \"%s\", \"name\": \"SUPERMAN\", \"race\": \"ALIEN\", \"strength\": 6, \"agility\": 5, \"dexterity\": 8, \"intelligence\": 10 } ", heroCreated.getId().toString());
        resultActions.andExpect(status().isCreated()).andExpect(content().json(json));

        heroRepository.deleteById(heroCreated.getId());
        powerStatsRepository.deleteById(heroCreated.getPowerStatsId());
    }

    private HeroDTO createHeroRequest() {
        return HeroDTO.builder()
                .name("Superman")
                .agility(5)
                .dexterity(8)
                .strength(6)
                .intelligence(10)
                .race(Race.ALIEN)
                .build();
    }

    @Test
    void shouldReturnHeroById() throws Exception {
        //when
        var result = mockMvc.perform(get("/api/v1/heroes/id/{id}", hero.getId())).andExpect(status().isOk());
        //then
        result.andExpect(jsonPath("$.id").value(hero.getId().toString()));
    }

    @Test
    void shouldReturnHeroByName() throws Exception {
        //when
        var result = mockMvc.perform(get("/api/v1/heroes/name/{name}", hero.getName())).andExpect(status().isOk());
        //then
        result.andExpect(jsonPath("$.name").value("BATMAN"));
    }

    @Test
    void shouldDeleteHeroById() throws Exception {
        //when
        var result = mockMvc.perform(delete("/api/v1/heroes/id/{id}", hero.getId())).andExpect(status().isNoContent());
        //then
        var exception = assertThrows(HeroByIdNotFoundException.class, () -> heroRepository.findById(hero.getId()));
        assertEquals("Hero not found: " + hero.getId(), exception.getMessage());
    }

    @Test
    void shouldDeleteHeroByName() throws Exception {
        //when
        var result = mockMvc.perform(delete("/api/v1/heroes/name/{name}", hero.getName())).andExpect(status().isNoContent());
        //then
        var exception = assertThrows(HeroByNameNotFoundException.class, () -> heroRepository.findByName(hero.getName()));
        assertEquals("Hero not found: BATMAN", exception.getMessage());
    }

    @Test
    void shouldCompareHeroes() throws Exception {
        //given
        var secondPowerStats = PowerStats.builder()
                .agility(7)
                .dexterity(3)
                .strength(8)
                .intelligence(5)
                .build();
        secondPowerStats = powerStatsRepository.create(secondPowerStats);
        var secondHero = Hero.builder()
                .name("SUPERMAN")
                .race(Race.ALIEN)
                .powerStatsId(secondPowerStats.getId())
                .build();
        secondHero = heroRepository.create(secondHero);

        var json = String.format("{\"first_id\": \"%s\",\n" +
                "    \"first_strength\": -5,\n" +
                "    \"first_agility\": -3,\n" +
                "    \"first_dexterity\": 4,\n" +
                "    \"first_intelligence\": 6,\n" +
                "    \"second_id\": \"%s\",\n" +
                "    \"second_strength\": 8,\n" +
                "    \"second_agility\": 7,\n" +
                "    \"second_dexterity\": -3,\n" +
                "    \"second_intelligence\": -5 }", hero.getId().toString(), secondHero.getId().toString());

        //when
        var result = mockMvc.perform(get("/api/v1/heroes/compare/{firstHero}/with/{secondHero}", hero.getName(), secondHero.getName()));
        //then
        result.andExpect(content().json(json));
        heroRepository.deleteById(secondHero.getId());
        powerStatsRepository.deleteById(secondPowerStats.getId());

    }


}