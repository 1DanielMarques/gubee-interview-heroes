package br.com.gubee.interview.core.features.hero.resource.assembler;

import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.dto.HeroDTO;
import br.com.gubee.interview.model.enums.Race;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssemblerTest {

    private final Assembler assembler = new Assembler();


    @Test
    void shouldReturnHeroFromHeroDTO() {
        //given
        var heroDTO = HeroDTO.builder()
                .name("batman")
                .race("human")
                .agility(2)
                .dexterity(4)
                .strength(5)
                .intelligence(6)
                .build();

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


}
