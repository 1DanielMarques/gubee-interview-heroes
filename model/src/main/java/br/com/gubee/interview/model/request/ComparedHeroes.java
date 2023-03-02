package br.com.gubee.interview.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComparedHeroes {

    private UUID firstId;
    private int firstStrength;
    private int firstAgility;
    private int firstDexterity;
    private int firstIntelligence;

    private UUID secondId;
    private int secondStrength;
    private int secondAgility;
    private int secondDexterity;
    private int secondIntelligence;


}
