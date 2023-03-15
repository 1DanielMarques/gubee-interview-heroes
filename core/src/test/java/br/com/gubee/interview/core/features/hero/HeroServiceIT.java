package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.exception.HeroByIdNotFoundException;
import br.com.gubee.interview.core.exception.HeroByNameNotFoundException;
import br.com.gubee.interview.model.enums.Race;
import br.com.gubee.interview.model.request.HeroRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("it")
public class HeroServiceIT {

    @Autowired
    private HeroService heroService;
    private final UUID randomHeroId = UUID.randomUUID();

    private HeroRequest heroRequest;
    private String heroName = "Batman";


    @BeforeEach
    void setup() {
        this.heroRequest = createHeroRequest();
        try {
            heroService.create(this.heroRequest);
        } catch (Exception e) {
            heroService.deleteByName(heroName);
            heroService.create(this.heroRequest);
        }
    }

    @Test
    @DisplayName("To create a Hero with all required arguments")
    void createHeroWithAllRequiredArguments() {
        HeroRequest hero;
        try {
            hero = heroService.create(this.heroRequest);
        } catch (DuplicateKeyException e) {
            heroService.deleteByName(heroName);
        }
        assertSame(HeroRequest.class, heroService.create(this.heroRequest).getClass());
    }

    @Test
    @DisplayName("Should return a Hero with its attributes from findById")
    void shouldReturnHeroFromFindById() {
        UUID heroId = heroService.getHeroIdByName(heroName);
        assertEquals(this.heroRequest, heroService.findById(heroId));
    }


    @Test
    @DisplayName("Should throw a HeroByIdNotFoundException from findById when id doesn't exist")
    void shouldThrowExceptionFromFindById() {
        HeroByIdNotFoundException exception = assertThrows(HeroByIdNotFoundException.class, () -> heroService.findById(randomHeroId));
        assertEquals("Hero not found: " + randomHeroId, exception.getMessage());
    }

    @Test
    @DisplayName("Should return a Hero with its attributes from findByName")
    void shouldReturnHeroFromFindByName() {
        assertEquals(this.heroRequest, heroService.findByName(heroName));
    }

    @Test
    @DisplayName("Should throw a HeroByNameNotFoundException from findByName when name doesn't exist")
    void shouldThrowExceptionFromFindByName() {
        HeroByNameNotFoundException exception = assertThrows(HeroByNameNotFoundException.class, () -> heroService.findByName("SpiderMan"));
        assertEquals("Hero not found: SpiderMan", exception.getMessage());
    }

    @Test
    @DisplayName("Should update all Hero attributes by id")
    void shouldUpdateAllHeroAttributes() {
        HeroRequest heroToUpdate = HeroRequest.builder()
                .name(heroName)
                .agility(1)
                .dexterity(2)
                .strength(3)
                .intelligence(4)
                .race(Race.ALIEN)
                .build();
        UUID heroId = heroService.getHeroIdByName(heroName);
        heroService.updateById(heroId, heroToUpdate);
        HeroRequest updatedHero = heroService.findById(heroId);
        assertEquals(heroToUpdate, updatedHero);
    }

    @Test
    @DisplayName("Should update only one hero attribute")
    void shouldUpdateOnlyOneAttribute() {
        int attribute = 7;
        UUID heroId;
        try {
            heroId = heroService.getHeroIdByName(heroName);
        } catch (EmptyResultDataAccessException e) {
            heroService.create(heroRequest);
            heroId = heroService.getHeroIdByName(heroName);
        }
        HeroRequest oldHero = heroService.findById(heroId);
        HeroRequest heroToUpdate = HeroRequest.builder()
                .strength(attribute) //Attribute to be updated
                .build();
        heroService.updateById(heroId, heroToUpdate);
        HeroRequest updatedHero = heroService.findById(heroId);
        assertEquals(attribute, updatedHero.getStrength());
    }


    @Test
    @DisplayName("Should throw a HeroByIdNotFoundException from UpdateById when id doesn't exist")
    void shouldThrowExceptionFromUpdateById() {
        HeroByIdNotFoundException exception = assertThrows(HeroByIdNotFoundException.class, () -> heroService.updateById(randomHeroId, null));
        assertEquals("Hero not found: " + randomHeroId, exception.getMessage());
    }

    @Test
    void shouldDeleteHeroById() {
        UUID heroId = heroService.getHeroIdByName(heroName);
        heroService.deleteById(heroId);
        assertThrows(HeroByIdNotFoundException.class, () -> heroService.findById(heroId));
    }

    @Test
    @DisplayName("Should throw a HeroByIdNotFoundException from deleteById when id doesn't exist")
    void shouldThrowExceptionFromDeleteById() {
        HeroByIdNotFoundException exception = assertThrows(HeroByIdNotFoundException.class, () -> heroService.deleteById(randomHeroId));
        assertEquals("Hero not found: " + randomHeroId, exception.getMessage());
    }

    private HeroRequest createHeroRequest() {
        return HeroRequest.builder()
                .name(heroName)
                .agility(5)
                .dexterity(8)
                .strength(6)
                .intelligence(10)
                .race(Race.HUMAN)
                .build();
    }
}
