package br.com.gubee.interview.web.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ComparedHeroesDTO(
        UUID firstId,
        int firstStrength,
        int firstAgility,
        int firstDexterity,
        int firstIntelligence,
        UUID secondId,
        int secondStrength,
        int secondAgility,
        int secondDexterity,
        int secondIntelligence

) {


}
