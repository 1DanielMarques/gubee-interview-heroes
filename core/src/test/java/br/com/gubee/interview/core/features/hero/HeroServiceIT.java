package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.exception.HeroByIdNotFoundException;
import br.com.gubee.interview.core.exception.HeroByNameNotFoundException;
import br.com.gubee.interview.model.enums.Race;
import br.com.gubee.interview.model.request.HeroRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("it")
public class HeroServiceIT {

    @Autowired
    private HeroService heroService;
    private final UUID heroId = UUID.randomUUID();


    @Test
    @DisplayName("To create a Hero with all required arguments")
    void createHeroWithAllRequiredArguments() {
        try {
            heroService.deleteByName("Batman");
        } catch (HeroByNameNotFoundException e) {
            HeroByNameNotFoundException exception = assertThrows(HeroByNameNotFoundException.class, () -> heroService.findByName("Batman"));
            assertEquals("Hero not found: Batman", exception.getMessage());
        } finally {
            heroService.create(createHeroRequest());
        }

    }

    @Test
    @DisplayName("Should throw a HeroByIdNotFoundException from findById")
    void shouldThrowExceptionFromFindById() {
        HeroByIdNotFoundException exception = assertThrows(HeroByIdNotFoundException.class, () -> heroService.findById(heroId));
        assertEquals("Hero not found: " + heroId, exception.getMessage());
    }

    @Test
    @DisplayName("Should throw a HeroByNameNotFoundException from findByName")
    void shouldThrowExceptionFromFindByName() {
        HeroByNameNotFoundException exception = assertThrows(HeroByNameNotFoundException.class, () -> heroService.findByName("SpiderMan"));
        assertEquals("Hero not found: SpiderMan", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw a HeroByIdNotFoundException from UpdateById")
    void shouldThrowExceptionFromUpdateById() {
        HeroByIdNotFoundException exception = assertThrows(HeroByIdNotFoundException.class, () -> heroService.updateById(heroId, null));
        assertEquals("Hero not found: " + heroId, exception.getMessage());
    }

    @Test
    @DisplayName("Should throw a HeroByIdNotFoundException from deleteById")
    void shouldThrowExceptionFromDeleteById() {
        HeroByIdNotFoundException exception = assertThrows(HeroByIdNotFoundException.class, () -> heroService.deleteById(heroId));
        assertEquals("Hero not found: " + heroId, exception.getMessage());
    }

    @Test
    @DisplayName("Should throw a HeroByNameNotFoundException from deleteByName")
    void shouldThrowExceptionFromDeleteByName() {
        HeroByNameNotFoundException exception = assertThrows(HeroByNameNotFoundException.class, () -> heroService.deleteByName("SpiderMan"));
        assertEquals("Hero not found: SpiderMan", exception.getMessage());
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
}
