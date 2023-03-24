package br.com.gubee.interview.domain.usecase.hero;

import br.com.gubee.interview.domain.exceptions.HeroByIdNotFoundException;
import br.com.gubee.interview.domain.exceptions.HeroByNameNotFoundException;
import br.com.gubee.interview.domain.exceptions.ResourceNotFoundException;
import br.com.gubee.interview.domain.repository.HeroRepository;
import br.com.gubee.interview.domain.usecase.hero.interfaces.DeleteHero;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class DeleteHeroUseCase implements DeleteHero {

    private final HeroRepository heroRepository;

    @Override
    public void deleteById(UUID id) {
        try{
            heroRepository.deleteById(id);
        }catch (ResourceNotFoundException e){
            throw new HeroByIdNotFoundException(id);
        }

    }

    @Override
    public void deleteByName(String name) {
        try {
            heroRepository.deleteByName(name.toUpperCase());
        }catch (ResourceNotFoundException e){
            throw new HeroByNameNotFoundException(name);
        }
    }

}
