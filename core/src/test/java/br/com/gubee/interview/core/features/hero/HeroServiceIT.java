package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.exception.HeroByIdNotFoundException;
import br.com.gubee.interview.core.exception.HeroByNameNotFoundException;
import br.com.gubee.interview.model.enums.Race;
import br.com.gubee.interview.model.dto.HeroDTO;
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

/*@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("it")
public class HeroServiceIT {

    @Autowired
    private HeroService heroService;
    private final UUID randomHeroId = UUID.randomUUID();

    private HeroDTO heroDTO;
    private String heroName = "Batman";


    @BeforeEach
    void setup() {
        this.heroDTO = createHeroRequest();
        try {
            heroService.create(this.heroDTO);
        } catch (Exception e) {
            heroService.deleteByName(heroName);
            heroService.create(this.heroDTO);
        }
    }


    @Test
    @DisplayName("To create a Hero with all required arguments")
    void createHeroWithAllRequiredArguments() {
        HeroDTO hero;
        try {
            hero = heroService.create(this.heroDTO);
        } catch (DuplicateKeyException e) {
            heroService.deleteByName(heroName);
        }
        assertSame(HeroDTO.class, heroService.create(this.heroDTO).getClass());
    }

    @Test
    @DisplayName("Should return a Hero with its attributes from findById")
    void shouldReturnHeroFromFindById() {
        UUID heroId = heroService.getHeroIdByName(heroName);
        assertEquals(this.heroDTO, heroService.findById(heroId));
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
        assertEquals(this.heroDTO, heroService.findByName(heroName));
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
        HeroDTO heroToUpdate = HeroDTO.builder()
                .name(heroName)
                .agility(1)
                .dexterity(2)
                .strength(3)
                .intelligence(4)
                .race(Race.ALIEN)
                .build();
        UUID heroId = heroService.getHeroIdByName(heroName);
        heroService.updateById(heroId, heroToUpdate);
        HeroDTO updatedHero = heroService.findById(heroId);
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
            heroService.create(heroDTO);
            heroId = heroService.getHeroIdByName(heroName);
        }
        HeroDTO oldHero = heroService.findById(heroId);
        HeroDTO heroToUpdate = HeroDTO.builder()
                .strength(attribute) //Attribute to be updated
                .build();
        heroService.updateById(heroId, heroToUpdate);
        HeroDTO updatedHero = heroService.findById(heroId);
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

    private HeroDTO createHeroRequest() {
        return HeroDTO.builder()
                .name(heroName)
                .agility(5)
                .dexterity(8)
                .strength(6)
                .intelligence(10)
                .race(Race.HUMAN)
                .build();
    }
}*/
