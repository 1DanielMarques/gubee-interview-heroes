package br.com.gubee.interview.domain.exceptions;

public class FailedCreateHeroException extends RuntimeException{
    public FailedCreateHeroException(){
        super("There was an error creating Hero, try again!");
    }
}
