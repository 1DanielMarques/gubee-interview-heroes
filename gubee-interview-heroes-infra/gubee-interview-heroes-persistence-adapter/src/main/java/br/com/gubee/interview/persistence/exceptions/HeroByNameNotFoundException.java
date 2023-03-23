package br.com.gubee.interview.persistence.exceptions;

public class HeroByNameNotFoundException extends RuntimeException {
    public HeroByNameNotFoundException(String name) {
        super("Hero not found: " + name);
    }
}