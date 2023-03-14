package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.model.enums.Race;
import br.com.gubee.interview.model.request.HeroRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HeroController.class)
class HeroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private HeroService heroService;

    private HeroRequest heroRequest;

    @BeforeEach
    public void initTest() {
        when(heroService.create(any())).thenReturn(createHeroRequest());

    }

    @BeforeEach
    public void heroRequestWithId() {
        heroRequest = createHeroRequest();
        heroRequest.setId(UUID.randomUUID());
    }


    @Test
    void createAHeroWithAllRequiredArguments() throws Exception {
        //given
        // Convert the hero request into a string JSON format stub.
        final String body = objectMapper.writeValueAsString(createHeroRequest());

        //when
        final ResultActions resultActions = mockMvc.perform(post("/api/v1/heroes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body));

        //then
        resultActions.andExpect(status().isCreated()).andExpect(header().exists("Location"));
        verify(heroService, times(1)).create(any());
    }

    private HeroRequest createHeroRequest() {
        return HeroRequest.builder()
                .name("Batman")
                .agility(5)
                .dexterity(8)
                .strength(6)
                .intelligence(10)
                .race(Race.HUMAN)
                .build();
    }

    @Test
    void findAllHeroesWithItsAttributes() throws Exception {
        when(heroService.findAll()).thenReturn(List.of(heroRequest));
        //when
        final ResultActions resultActions = mockMvc.perform(get("/api/v1/heroes")).andExpect(status().isOk());

        var json = String.format("[ { \"id\": \"%s\", \"name\": \"Batman\", \"race\": \"HUMAN\", \"strength\": 6, \"agility\": 5, \"dexterity\": 8, \"intelligence\": 10 } ]", heroRequest.getId().toString());
        //then
        resultActions.andExpect(status().isOk()).andExpect(content().json(json));
    }

    @Test
    void findHeroByIdWithItsAttributes() throws Exception {
        when(heroService.findById(any())).thenReturn(this.heroRequest);

        var url = String.format("/api/v1/heroes/id/%s", heroRequest.getId().toString());
        //when
        final ResultActions resultActions = mockMvc.perform(get(url)).andExpect(status().isOk());

        var json = String.format(" { \"id\": \"%s\", \"name\": \"Batman\", \"race\": \"HUMAN\", \"strength\": 6, \"agility\": 5, \"dexterity\": 8, \"intelligence\": 10 } ", heroRequest.getId().toString());
        //then
        resultActions.andExpect(status().isOk()).andExpect(content().json(json));
    }

}