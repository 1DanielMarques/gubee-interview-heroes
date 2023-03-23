package br.com.gubee.interview.persistence.exceptions;

import java.util.UUID;

public class PowerStatsByIdNotFoundException extends RuntimeException {
    public PowerStatsByIdNotFoundException(UUID id) {
        super("Power stats not found: " + id);
    }
}