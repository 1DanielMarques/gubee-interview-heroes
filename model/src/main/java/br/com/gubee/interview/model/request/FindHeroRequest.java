package br.com.gubee.interview.model.request;

import br.com.gubee.interview.model.enums.Race;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindHeroRequest {

    private String name;
    private Race race;
    private int strength;
    private int agility;
    private int dexterity;
    private int intelligence;


}
