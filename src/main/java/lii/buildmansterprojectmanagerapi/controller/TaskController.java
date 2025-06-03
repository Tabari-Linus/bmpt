package lii.buildmansterprojectmanagerapi.controller;


import lii.buildmansterprojectmanagerapi.dto.request.TaskRequest;
import lii.buildmansterprojectmanagerapi.dto.response.TaskResponse;
import lii.buildmansterprojectmanagerapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest request) {
        return ResponseEntity.ok(taskService.create(request));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<TaskResponse>> getTasksByProject(@PathVariable Long projectId) {
        return ResponseEntity.ok(taskService.getByProject(projectId));
    }

    @GetMapping("/developer/{developerId}")
    public ResponseEntity<List<TaskResponse>> getTasksByDeveloper(@PathVariable Long developerId) {
        return ResponseEntity.ok(taskService.getByDeveloper(developerId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable Long id,
            @RequestBody TaskRequest request) {
        return ResponseEntity.ok(taskService.updateTask(id, request));
    }

}
