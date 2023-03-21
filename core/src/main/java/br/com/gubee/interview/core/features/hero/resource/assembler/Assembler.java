package br.com.gubee.interview.core.features.hero.resource.assembler;

import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.model.dto.HeroDTO;
import br.com.gubee.interview.model.enums.Race;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Assembler {

    public Hero toHeroDomain(HeroDTO heroDTO, UUID powerStatsId) {
        // Is this method clean or not?
        heroDTO.setRace((heroDTO.getRace() != null) ? heroDTO.getRace().toUpperCase() : null);
        heroDTO.setName((heroDTO.getName() != null) ? heroDTO.getName().toUpperCase() : null);
        var race = switch (heroDTO.getRace()) {
            case "HUMAN" -> Race.HUMAN;
            case "ALIEN" -> Race.ALIEN;
            case "DIVINE" -> Race.DIVINE;
            case "CYBORG" -> Race.CYBORG;
            case null -> null;
            default -> throw new IllegalArgumentException("Invalid Race of Hero: " + heroDTO.getRace());
        };

        return Hero.builder()
                .name(heroDTO.getName())
                .race(race)
                .powerStatsId(powerStatsId)
                .build();
    }

    public PowerStats toPowerStatsDomain(HeroDTO heroDTO) {
        return PowerStats.builder()
                .agility(heroDTO.getAgility())
                .dexterity(heroDTO.getDexterity())
                .strength(heroDTO.getStrength())
                .intelligence(heroDTO.getIntelligence())
                .build();
    }

    public List<HeroDTO> toDTOList(List<Hero> heroList, List<PowerStats> powerStatsList) {
        List<HeroDTO> heroDTOList = new ArrayList<>();
        for (var hero : heroList) {
            for (var powerStats : powerStatsList) {
                if (hero.getPowerStatsId().equals(powerStats.getId())) {
                    var heroDTO = HeroDTO.builder()
                            .id(hero.getId())
                            .name(hero.getName())
                            .race(hero.getRace().toString())
                            .agility(powerStats.getAgility())
                            .dexterity(powerStats.getDexterity())
                            .strength(powerStats.getStrength())
                            .intelligence(powerStats.getIntelligence())
                            .build();
                    heroDTOList.add(heroDTO);
                    break;
                }
            }
        }
        return heroDTOList;
    }

    public HeroDTO toHeroDTO(Hero hero, PowerStats powerStats) {
        var heroDTO = HeroDTO.builder()
                .id(hero.getId())
                .name(hero.getName())
                .race(hero.getRace().toString())
                .agility(powerStats.getAgility())
                .dexterity(powerStats.getDexterity())
                .strength(powerStats.getStrength())
                .intelligence(powerStats.getIntelligence())
                .build();

        return heroDTO;
    }

}
