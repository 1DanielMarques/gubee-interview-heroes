package br.com.gubee.interview.core.exception;

import java.util.UUID;

public class HeroByNameNotFound extends RuntimeException {
    public HeroByNameNotFound(String name) {
        super("Hero not found: " + name);
    }
}
