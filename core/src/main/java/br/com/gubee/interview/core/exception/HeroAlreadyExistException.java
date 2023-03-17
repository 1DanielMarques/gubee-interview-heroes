package br.com.gubee.interview.core.exception;

public class HeroAlreadyExistException extends RuntimeException {
    public HeroAlreadyExistException(String name) {
        super("Hero "+name+" already exist");
    }
}
