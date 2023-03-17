package br.com.gubee.interview.core.exception;

import java.util.UUID;

public class PowerStatsByIdNotFoundException extends RuntimeException {
    public PowerStatsByIdNotFoundException(UUID id) {
        super("Power stats not found: " + id);
    }
}
