package br.com.gubee.interview.core.exception;

public class HeroByNameNotFoundException extends RuntimeException {
    public HeroByNameNotFoundException(String name) {
        super("Hero not found: " + name);
    }
}
