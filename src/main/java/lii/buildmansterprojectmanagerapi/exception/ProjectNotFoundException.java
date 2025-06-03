package lii.buildmansterprojectmanagerapi.exception;

public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException(Long id) {
        super("Project with id: " + id + " not found");
    }

    public ProjectNotFoundException(String message) {
        super(message);
    }

}
