package mateuszteam.final_project.exceptions;

public class CopiesNotFoundException extends RuntimeException {

    public CopiesNotFoundException(String msg) {
        super(msg);
    }

    public CopiesNotFoundException(String msg, Long id) {
        super(msg);
    }
}
