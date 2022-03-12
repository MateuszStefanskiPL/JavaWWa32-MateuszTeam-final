package mateuszteam.final_project.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Long resourceId) {
        super(String.format("Resource with ID=%d not found", resourceId));
    }

    public ResourceNotFoundException(String searchParam) {
        super(String.format("Resource looked up by '%s' not found", searchParam));
    }

}
