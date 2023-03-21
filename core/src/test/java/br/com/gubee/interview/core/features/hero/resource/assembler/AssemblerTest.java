package br.com.gubee.interview.core.features.hero.resource.assembler;

import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.model.dto.HeroDTO;
import br.com.gubee.interview.model.enums.Race;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssemblerTest {

    private final Assembler assembler = new Assembler();


    @Test
    void shouldReturnHeroFromHeroDTO() {
        //given
        var heroDTO = createHeroDTO();
        var powerStatsId = UUID.randomUUID();
        var heroExpected = Hero.builder()
                .name("BATMAN")
                .race(Race.HUMAN)
                .powerStatsId(powerStatsId)
                .build();
        //when
        var hero = assembler.toHeroDomain(heroDTO, powerStatsId);
        //then
        assertEquals(heroExpected, hero);
    }

    private HeroDTO createHeroDTO() {
        return HeroDTO.builder()
                .name("batman")
                .race("human")
                .agility(2)
                .dexterity(4)
                .strength(5)
                .intelligence(6)
                .build();
    }


    @Test
    void shouldReturnPowerStatsFromHeroDTO() {
        //given
        var heroDTO = createHeroDTO();

        var powerStatsExpected = PowerStats.builder()
                .agility(2)
                .dexterity(4)
                .strength(5)
                .intelligence(6)
                .build();
        //when
        var powerStats = assembler.toPowerStatsDomain(heroDTO);
        //then
        assertEquals(powerStatsExpected, powerStats);

    }

    @Test
    void shouldReturnDTOFromDomain() {
        //given
        var hero = Hero.builder()
                .name("BATMAN")
                .race(Race.HUMAN)
                .build();
        var powerStats = PowerStats.builder()
                .agility(2)
                .dexterity(4)
                .strength(5)
                .intelligence(6)
                .build();
        var heroDTOExpected = HeroDTO.builder()
                .name("BATMAN")
                .race("HUMAN")
                .agility(2)
                .dexterity(4)
                .strength(5)
                .intelligence(6)
                .build();
        //when
        var heroDTO = assembler.toHeroDTO(hero, powerStats);
        //then
        assertEquals(heroDTOExpected, heroDTO);
    }

    @Test
    void shouldReturnListOfDTO() {
        //given
        var firstPowerStats = PowerStats.builder()
                .id(UUID.randomUUID())
                .agility(2)
                .dexterity(4)
                .strength(5)
                .intelligence(6)
                .build();
        var firstHeroId = UUID.randomUUID();
        var firstHero = Hero.builder()
                .id(firstHeroId)
                .name("BATMAN")
                .race(Race.HUMAN)
                .powerStatsId(firstPowerStats.getId())
                .build();

        var secondPowerStats = PowerStats.builder()
                .id(UUID.randomUUID())
                .agility(5)
                .dexterity(3)
                .strength(7)
                .intelligence(4)
                .build();
        var secondHeroId = UUID.randomUUID();
        var secondHero = Hero.builder()
                .id(secondHeroId)
                .name("SUPERMAN")
                .race(Race.ALIEN)
                .powerStatsId(secondPowerStats.getId())
                .build();
        List<Hero> heroList = Arrays.asList(firstHero, secondHero);
        List<PowerStats> powerStatsList = Arrays.asList(firstPowerStats, secondPowerStats);

        var firstDTO = HeroDTO.builder()
                .id(firstHeroId)
                .name("BATMAN")
                .race("HUMAN")
                .agility(2)
                .dexterity(4)
                .strength(5)
                .intelligence(6)
                .build();
        var secondDTO = HeroDTO.builder()
                .id(secondHeroId)
                .name("SUPERMAN")
                .race("ALIEN")
                .agility(5)
                .dexterity(3)
                .strength(7)
                .intelligence(4)
                .build();

        List<HeroDTO> heroDTOListExpected = Arrays.asList(firstDTO, secondDTO);
        //when
       var heroDTOList =  assembler.toDTOList(heroList, powerStatsList);
        //then

        assertEquals(heroDTOListExpected,heroDTOList);
    }

}
