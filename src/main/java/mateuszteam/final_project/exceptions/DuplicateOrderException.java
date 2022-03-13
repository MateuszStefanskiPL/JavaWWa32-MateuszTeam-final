package mateuszteam.final_project.exceptions;

public class DuplicateOrderException extends RuntimeException {

    public DuplicateOrderException(final String message) {
        super(message);
    }
}
