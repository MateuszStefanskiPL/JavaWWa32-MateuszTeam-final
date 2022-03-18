package mateuszteam.final_project.exceptions;

public class CopyNotFoundException extends RuntimeException {

    public CopyNotFoundException(Long copyId) {
        super(String.format("Movie copy with ID=%d not found", copyId));
    }
}
