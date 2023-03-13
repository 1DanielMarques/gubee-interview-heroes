package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.model.enums.Race;
import br.com.gubee.interview.model.request.HeroRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("it")
public class HeroServiceIT {

    @Autowired
    private HeroService heroService;


    @Test
    public void createHeroWithAllRequiredArguments() {
        heroService.deleteById(heroService.getHeroIdByName("Batman"));
        heroService.create(createHeroRequest());
    }

    private HeroRequest createHeroRequest() {
        return HeroRequest.builder()
                .name("Batman")
                .agility(5)
                .dexterity(8)
                .strength(6)
                .intelligence(10)
                .race(Race.HUMAN)
                .build();
    }
}
