package br.com.gubee.interview.resource.controller;

import br.com.gubee.interview.domain.enums.Race;
import br.com.gubee.interview.domain.spi.hero.HeroRepositoryStub;
import br.com.gubee.interview.resource.dto.HeroDTO;
import br.com.gubee.interview.resource.facade.HeroFacade;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HeroController.class)
class HeroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private HeroFacade heroFacade;

    private HeroDTO heroDTO;
    private final HeroRepositoryStub repositoryStub = new HeroRepositoryStub();

    @BeforeEach
    public void initTest() {
        when(heroFacade.create(any())).thenReturn(createHeroRequest());

    }

    @BeforeEach
    public void heroRequestWithId() {
        heroDTO = createHeroRequest();
        heroDTO.setId(UUID.randomUUID());
    }


    @Test
    @DisplayName("Should Create Hero with all required arguments")
    void shouldCreateAHeroWithAllRequiredArguments() throws Exception {
        //given
        // Convert the hero request into a string JSON format stub.
        final String body = objectMapper.writeValueAsString(createHeroRequest());

        //when

        final ResultActions resultActions = mockMvc.perform(post("/api/v1/heroes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body));

        //then
        //resultActions.andExpect(status().isCreated()).andExpect(header().exists("Location"));
        var json = String.format(" { \"name\": \"Batman\", \"race\": \"HUMAN\", \"strength\": 6, \"agility\": 5, \"dexterity\": 8, \"intelligence\": 10 } ", heroDTO.getId().toString());
        resultActions.andExpect(status().isCreated()).andExpect(content().json(json));
        verify(heroFacade, times(1)).create(any());
    }


    private HeroDTO createHeroRequest() {
        return HeroDTO.builder()
                .name("Batman")
                .agility(5)
                .dexterity(8)
                .strength(6)
                .intelligence(10)
                .race(Race.HUMAN)
                .build();
    }

    @Test
    @DisplayName("Should return a list of all Heroes with its attributes")
    void shouldReturnListOfAllHeroesWithItsAttributes() throws Exception {
        //given
        when(heroFacade.findAll()).thenReturn(List.of(heroDTO));
        var json = String.format("[ { \"id\": \"%s\", \"name\": \"Batman\", \"race\": \"HUMAN\", \"strength\": 6, \"agility\": 5, \"dexterity\": 8, \"intelligence\": 10 } ]", heroDTO.getId().toString());

        //when
        final ResultActions resultActions = mockMvc.perform(get("/api/v1/heroes")).andExpect(status().isOk());

        //then
        resultActions.andExpect(status().isOk()).andExpect(content().json(json));
    }

    @Test
    @DisplayName("Should find a Hero and its attributes by id")
    void shouldFindHeroByIdWithItsAttributes() throws Exception {
        //given
        when(heroFacade.findById(any())).thenReturn(this.heroDTO);
        var json = String.format(" { \"id\": \"%s\", \"name\": \"Batman\", \"race\": \"HUMAN\", \"strength\": 6, \"agility\": 5, \"dexterity\": 8, \"intelligence\": 10 } ", heroDTO.getId().toString());

        //when
        var url = String.format("/api/v1/heroes/id/%s", heroDTO.getId());
        final ResultActions resultActions = mockMvc.perform(get(url)).andExpect(status().isOk());

        //then
        resultActions.andExpect(status().isOk()).andExpect(content().json(json));
    }

    @Test
    @DisplayName("Should find a hero and its attributes by name")
    void shouldFindHeroByNameWithItsAttributes() throws Exception {
        //given
        when(heroFacade.findByName(any())).thenReturn(this.heroDTO);
        var json = String.format(" { \"id\": \"%s\", \"name\": \"Batman\", \"race\": \"HUMAN\", \"strength\": 6, \"agility\": 5, \"dexterity\": 8, \"intelligence\": 10 } ", heroDTO.getId().toString());

        //when
        var url = String.format("/api/v1/heroes/name/%s", this.heroDTO.getName());
        final ResultActions resultActions = mockMvc.perform(get(url)).andExpect(status().isOk());

        //then
        resultActions.andExpect(content().json(json));
    }


   /*
   How to do?
   @Test
    void shouldUpdateHeroById() throws Exception {
        //given
        final String body = objectMapper.writeValueAsString(this.heroDTO);
        //when
        var url = String.format("/api/v1/heroes/id/%s", this.heroDTO.getId().toString());
        final ResultActions resultActions = mockMvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body));
        //then
        //org.json.JSONException: Unparsable JSON string:
        String json = "{\"name\":\"Batman\",\"race\":\"HUMAN\",\"strength\":6,\"agility\":5,\"dexterity\":8,\"intelligence\":10}";
        resultActions.andExpect(status().isOk()).andExpect(content().json(json));
        verify(heroFacade, times(1)).updateById(any(), any());
    } */


    @Test
    void shouldDeleteHeroById() throws Exception {
        //given
        var url = String.format("/api/v1/heroes/id/%s", this.heroDTO.getId().toString());
        //when
        final ResultActions resultActions = mockMvc.perform(delete(url));
        //then
        resultActions.andExpect(status().isNoContent());
        verify(heroFacade, times(1)).deleteById(any());
    }


}