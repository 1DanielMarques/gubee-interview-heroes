package br.com.gubee.interview.core.features.usecase.hero;

import br.com.gubee.interview.core.exception.HeroByIdNotFoundException;
import br.com.gubee.interview.core.features.hero.HeroRepository;
import br.com.gubee.interview.core.features.usecase.hero.interfaces.CreateHero;
import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.entities.HeroEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;

@RequiredArgsConstructor
public class CreateHeroUseCase implements CreateHero {

    private final HeroRepository heroRepository;

    @Override
    public Hero create(Hero hero) {
        //verificar ID nulo
        // verificar se já existe
        try {
            return heroRepository.create(HeroEntity.fromHero(hero)).toHero();
        } catch (EmptyResultDataAccessException e) {
            //Lançar outra
            throw new HeroByIdNotFoundException(hero.getId());
        }
    }
}
