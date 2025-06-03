package lii.buildmansterprojectmanagerapi.exception;

public class DeveloperNotFoundException extends RuntimeException {
    public DeveloperNotFoundException(Long id) {
        super("Project with id: " + id + " not found");
    }

    public DeveloperNotFoundException(String message) {
        super(message);
    }
}
