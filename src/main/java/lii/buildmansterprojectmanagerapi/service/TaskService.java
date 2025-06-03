package lii.buildmansterprojectmanagerapi.service;

import lii.buildmansterprojectmanagerapi.dto.request.TaskRequest;
import lii.buildmansterprojectmanagerapi.dto.response.TaskResponse;
import lii.buildmansterprojectmanagerapi.entity.jpa.Developer;
import lii.buildmansterprojectmanagerapi.entity.jpa.Project;
import lii.buildmansterprojectmanagerapi.entity.jpa.Task;
import lii.buildmansterprojectmanagerapi.exception.DeveloperNotFoundException;
import lii.buildmansterprojectmanagerapi.exception.ProjectNotFoundException;
import lii.buildmansterprojectmanagerapi.exception.ResourceNotFoundException;
import lii.buildmansterprojectmanagerapi.repository.jpa.DeveloperRepository;
import lii.buildmansterprojectmanagerapi.repository.jpa.ProjectRepository;
import lii.buildmansterprojectmanagerapi.repository.jpa.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final DeveloperRepository developerRepository;

    public TaskResponse create(TaskRequest request) {
        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new ProjectNotFoundException("Project not found"));

        Developer developer = null;
        if (request.getDeveloperId() != null) {
            developer = developerRepository.findById(request.getDeveloperId())
                    .orElseThrow(() -> new DeveloperNotFoundException("Developer not found"));
        }

        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(request.getStatus())
                .dueDate(request.getDueDate())
                .project(project)
                .developer(developer)
                .build();

        Task saved = taskRepository.save(task);

        return TaskResponse.builder()
                .id(saved.getId())
                .title(saved.getTitle())
                .description(saved.getDescription())
                .status(saved.getStatus())
                .dueDate(saved.getDueDate())
                .projectId(project.getId())
                .developerId(developer != null ? developer.getId() : null)
                .build();
    }

    public List<TaskResponse> getAll() {
        return taskRepository.findAll()
                .stream()
                .map(t -> TaskResponse.builder()
                .id(t.getId())
                .title(t.getTitle())
                .description(t.getDescription())
                .dueDate(t.getDueDate())
                .status(t.getStatus())
                .build())
                .toList();
    }

    public List<TaskResponse> getByProject(Long projectId) {
        return taskRepository.findByProjectId(projectId).stream()
                .map(t -> TaskResponse.builder()
                        .id(t.getId())
                        .title(t.getTitle())
                        .description(t.getDescription())
                        .status(t.getStatus())
                        .dueDate(t.getDueDate())
                        .projectId(t.getProject().getId())
                        .developerId(t.getDeveloper() != null ? t.getDeveloper().getId() : null)
                        .build())
                .toList();
    }

    public List<TaskResponse> getByDeveloper(Long developerId) {
        return taskRepository.findByDeveloperId(developerId).stream()
                .map(t -> TaskResponse.builder()
                        .id(t.getId())
                        .title(t.getTitle())
                        .description(t.getDescription())
                        .status(t.getStatus())
                        .dueDate(t.getDueDate())
                        .projectId(t.getProject().getId())
                        .developerId(t.getDeveloper() != null ? t.getDeveloper().getId() : null)
                        .build())
                .toList();
    }

    public TaskResponse updateTask(Long id, TaskRequest request) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setDueDate(request.getDueDate());

        if (request.getProjectId() != null) {
            Project project = projectRepository.findById(request.getProjectId())
                    .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
            task.setProject(project);
        }

        if (request.getDeveloperId() != null) {
            Developer dev = developerRepository.findById(request.getDeveloperId())
                    .orElseThrow(() -> new ResourceNotFoundException("Developer not found"));
            task.setDeveloper(dev);
        }

        Task updated = taskRepository.save(task);
        return TaskResponse.builder()
                .id(updated.getId())
                .title(updated.getTitle())
                .description(updated.getDescription())
                .status(updated.getStatus())
                .dueDate(updated.getDueDate())
                .projectId(updated.getProject().getId())
                .developerId(updated.getDeveloper() != null ? updated.getDeveloper().getId() : null)
                .build();
    }

}
