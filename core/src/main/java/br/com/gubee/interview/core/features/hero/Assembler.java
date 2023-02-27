package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.model.request.CreateHeroRequest;

public class Assembler {

    public PowerStats fromRequestToPowerStats(CreateHeroRequest request) {
        return PowerStats.builder()
                .strength(request.getStrength())
                .agility(request.getAgility())
                .dexterity(request.getDexterity())
                .intelligence(request.getIntelligence()).build();
    }

}
