package br.com.gubee.interview.core.exception;

import java.util.UUID;

public class HeroByIdNotFound extends RuntimeException {
    public HeroByIdNotFound(UUID id) {
        super("Hero not found: " + id);
    }
}
