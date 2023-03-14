package br.com.gubee.interview.core.exception;

import java.util.UUID;

public class HeroByIdNotFoundException extends RuntimeException {
    public HeroByIdNotFoundException(UUID id) {
        super("Hero not found: " + id);
    }
}
