package lii.buildmansterprojectmanagerapi.service;

import jakarta.transaction.Transactional;
import lii.buildmansterprojectmanagerapi.dto.request.ProjectRequest;
import lii.buildmansterprojectmanagerapi.dto.response.ProjectResponse;
import lii.buildmansterprojectmanagerapi.entity.jpa.Project;
import lii.buildmansterprojectmanagerapi.enums.ProjectStatus;
import lii.buildmansterprojectmanagerapi.exception.ProjectNotFoundException;
import lii.buildmansterprojectmanagerapi.exception.ResourceNotFoundException;
import lii.buildmansterprojectmanagerapi.repository.jpa.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectResponse createProject(ProjectRequest request) {
        Project project = Project.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .deadline(request.getDeadline())
                .projectStatus(request.getStatus())
                .build();

        Project saved = projectRepository.save(project);

        return ProjectResponse.builder()
                .id(saved.getId())
                .title(saved.getTitle())
                .description(saved.getDescription())
                .deadline(saved.getDeadline())
                .status(saved.getProjectStatus())
                .build();
    }

    public List<ProjectResponse> findAllProjects() {
        return projectRepository.findAll()
                .stream()
                .map(p -> ProjectResponse.builder()
                        .id(p.getId())
                        .title(p.getTitle())
                        .description(p.getDescription())
                        .deadline(p.getDeadline())
                        .status(p.getProjectStatus())
                        .build())
                .toList();
    }

    public ProjectResponse findProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(id));
        return ProjectResponse.builder()
                .id(project.getId())
                .title(project.getTitle())
                .description(project.getDescription())
                .deadline(project.getDeadline())
                .status(project.getProjectStatus())
                .build();
    }

    @Transactional
    public void deleteProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(id));
        projectRepository.delete(project);
    }

    public ProjectResponse updateProject(Long id, ProjectRequest request) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(id));

        project.setTitle(request.getTitle());
        project.setDescription(request.getDescription());
        project.setDeadline(request.getDeadline());
        project.setProjectStatus(request.getStatus());

        Project updated = projectRepository.save(project);
        return ProjectResponse.builder()
                .id(updated.getId())
                .title(updated.getTitle())
                .description(updated.getDescription())
                .deadline(updated.getDeadline())
                .status(updated.getProjectStatus())
                .build();
    }

    public ProjectResponse updateProjectStatus(Long id,ProjectStatus status) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(id));

        project.setProjectStatus(status);

        Project updated = projectRepository.save(project);
        return ProjectResponse.builder()
                .id(updated.getId())
                .title(updated.getTitle())
                .description(updated.getDescription())
                .deadline(updated.getDeadline())
                .status(updated.getProjectStatus())
                .build();
    }

}

