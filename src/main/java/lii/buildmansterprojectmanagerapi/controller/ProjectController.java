package lii.buildmansterprojectmanagerapi.controller;


import jakarta.validation.Valid;
import lii.buildmansterprojectmanagerapi.dto.request.ProjectRequest;
import lii.buildmansterprojectmanagerapi.dto.response.ProjectResponse;
import lii.buildmansterprojectmanagerapi.enums.ProjectStatus;
import lii.buildmansterprojectmanagerapi.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@Valid @RequestBody ProjectRequest request) {
        return ResponseEntity.ok(projectService.createProject(request));
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> getAllProjects() {
        return ResponseEntity.ok(projectService.findAllProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProjectById(@Valid @PathVariable Long id) {
        return ResponseEntity.ok(projectService.findProjectById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@Valid @PathVariable Long id) {
        projectService.deleteProjectById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponse> updateProject(
            @Valid
            @PathVariable Long id,
            @RequestBody ProjectRequest request) {
        return ResponseEntity.ok(projectService.updateProject(id, request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProjectResponse> updateProjectStatus(
            @Valid
            @PathVariable Long id,
            @RequestParam(required = false) ProjectStatus status) {
        return ResponseEntity.ok(projectService.updateProjectStatus(id, status));
    }

    @GetMapping("/projects")
    public Page<ProjectResponse> getAllProjects(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "deadline") String sortBy
    ) {
        return projectService.getAllProjects(PageRequest.of(page, size, Sort.by(sortBy)));
    }

}
