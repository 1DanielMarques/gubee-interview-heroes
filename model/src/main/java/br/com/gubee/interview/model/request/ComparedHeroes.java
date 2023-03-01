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

    private UUID id_1;
    private int strength_1;
    private int agility_1;
    private int dexterity_1;
    private int intelligence_1;

    private UUID id_2;
    private int strength_2;
    private int agility_2;
    private int dexterity_2;
    private int intelligence_2;


}
