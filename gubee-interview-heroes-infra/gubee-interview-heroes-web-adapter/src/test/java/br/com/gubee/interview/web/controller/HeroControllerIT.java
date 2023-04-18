package br.com.gubee.interview.web.controller;

import br.com.gubee.interview.domain.enums.Race;
import br.com.gubee.interview.domain.exceptions.HeroByIdNotFoundException;
import br.com.gubee.interview.domain.exceptions.HeroByNameNotFoundException;
import br.com.gubee.interview.domain.model.hero.Hero;
import br.com.gubee.interview.domain.model.powerstats.PowerStats;
import br.com.gubee.interview.domain.spi.hero.HeroRepository;
import br.com.gubee.interview.domain.spi.powerstats.PowerStatsRepository;
import br.com.gubee.interview.web.dto.HeroDTO;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class HeroControllerIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private HeroRepository heroRepository;
    @Autowired
    private PowerStatsRepository powerStatsRepository;

    @Autowired
    ObjectMapper mapper;
    private Hero hero;

    @BeforeEach
    void up() {
        hero = createAndSaveHero("BATMAN-TEST");
    }

    @AfterEach
    void down() {
        heroRepository.deleteById(hero.getId());
        powerStatsRepository.deleteById(hero.getPowerStatsId());
    }

    private Hero createAndSaveHero(String name) {
        var createdPowerStats = PowerStats.builder()
                .agility(3)
                .dexterity(4)
                .strength(5)
                .intelligence(6)
                .build();
        createdPowerStats = powerStatsRepository.create(createdPowerStats);
        var createdHero = Hero.builder()
                .name(name)
                .race(Race.HUMAN)
                .powerStatsId(createdPowerStats.getId())
                .build();
        createdHero = heroRepository.create(createdHero);
        return createdHero;
    }

    @Test
    void shouldReturnListOfHeroes() throws Exception {
        //when
        var result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/heroes"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        //then
        result.andExpect(jsonPath("$[0].name").value("BATMAN-TEST"));
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
        var heroCreated = heroRepository.findByName("SUPERMAN-TEST");
        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(heroCreated.getId().toString()))
                .andExpect(jsonPath("$.name").value("SUPERMAN-TEST"))
                .andExpect(jsonPath("$.race").value("ALIEN"))
                .andExpect(jsonPath("$.agility").value(5))
                .andExpect(jsonPath("$.dexterity").value(8))
                .andExpect(jsonPath("$.strength").value(6))
                .andExpect(jsonPath("$.intelligence").value(10));


        heroRepository.deleteById(heroCreated.getId());
        powerStatsRepository.deleteById(heroCreated.getPowerStatsId());
    }

    private HeroDTO createHeroRequest() {
        return HeroDTO.builder()
                .name("SUPERMAN-TEST")
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
        result.andExpect(jsonPath("$.name").value("BATMAN-TEST"));
    }

    @Test
    void shouldDeleteHeroById() throws Exception {
        //given
        var heroToDelete = createAndSaveHero("SUPERMAN-TEST");
        //when
        var result = mockMvc.perform(delete("/api/v1/heroes/id/{id}", heroToDelete.getId())).andExpect(status().isNoContent());
        //then
        var exception = assertThrows(HeroByIdNotFoundException.class, () -> heroRepository.findById(heroToDelete.getId()));
        assertEquals("Hero not found: " + heroToDelete.getId(), exception.getMessage());

    }

    @Test
    void shouldDeleteHeroByName() throws Exception {
        //given
        var heroToDelete = createAndSaveHero("SUPERMAN-TEST");
        //when
        var result = mockMvc.perform(delete("/api/v1/heroes/name/{name}", heroToDelete.getName())).andExpect(status().isNoContent());
        //then
        var exception = assertThrows(HeroByNameNotFoundException.class, () -> heroRepository.findByName(heroToDelete.getName()));
        assertEquals("Hero not found: SUPERMAN-TEST", exception.getMessage());
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
                .name("SUPERMAN-TEST")
                .race(Race.ALIEN)
                .powerStatsId(secondPowerStats.getId())
                .build();
        secondHero = heroRepository.create(secondHero);


        //when
        var result = mockMvc.perform(get("/api/v1/heroes/compare/{firstHero}/with/{secondHero}", hero.getName(), secondHero.getName()));
        //then
        result.andExpect(jsonPath("$.first_id").value(hero.getId().toString()))
                .andExpect(jsonPath("$.first_agility").value(-3))
                .andExpect(jsonPath("$.first_dexterity").value(4))
                .andExpect(jsonPath("$.first_strength").value(-5))
                .andExpect(jsonPath("$.first_intelligence").value(6))
                .andExpect(jsonPath("$.second_id").value(secondHero.getId().toString()))
                .andExpect(jsonPath("$.second_agility").value(7))
                .andExpect(jsonPath("$.second_dexterity").value(-3))
                .andExpect(jsonPath("$.second_strength").value(8))
                .andExpect(jsonPath("$.second_intelligence").value(-5));
        heroRepository.deleteById(secondHero.getId());
        powerStatsRepository.deleteById(secondPowerStats.getId());
    }

}