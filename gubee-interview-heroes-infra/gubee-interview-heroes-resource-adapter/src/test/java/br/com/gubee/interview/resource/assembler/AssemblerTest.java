package br.com.gubee.interview.resource.assembler;

import br.com.gubee.interview.domain.enums.Race;
import br.com.gubee.interview.domain.model.hero.Hero;
import br.com.gubee.interview.domain.model.powerstats.PowerStats;
import br.com.gubee.interview.resource.dto.HeroDTO;
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
                .race(Race.HUMAN)
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
                .race(Race.HUMAN)
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
                .race(Race.HUMAN)
                .agility(2)
                .dexterity(4)
                .strength(5)
                .intelligence(6)
                .build();
        var secondDTO = HeroDTO.builder()
                .id(secondHeroId)
                .name("SUPERMAN")
                .race(Race.ALIEN)
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
