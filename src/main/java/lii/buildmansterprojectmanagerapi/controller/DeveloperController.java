package lii.buildmansterprojectmanagerapi.controller;


import lii.buildmansterprojectmanagerapi.dto.request.DeveloperRequest;
import lii.buildmansterprojectmanagerapi.dto.response.DeveloperResponse;
import lii.buildmansterprojectmanagerapi.service.DeveloperService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/developers")
@RequiredArgsConstructor
public class DeveloperController {

    private final DeveloperService developerService;

    @PostMapping
    public ResponseEntity<DeveloperResponse> createDeveloper(@RequestBody DeveloperRequest request) {
        return ResponseEntity.ok(developerService.createDeveloper(request));
    }

    @GetMapping
    public ResponseEntity<List<DeveloperResponse>> getAllDevelopers() {
        return ResponseEntity.ok(developerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeveloperResponse> getDeveloperById(@PathVariable Long id) {
        return ResponseEntity.ok(developerService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeveloper(@PathVariable Long id) {
        developerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeveloperResponse> updateDeveloper(
            @PathVariable Long id,
            @RequestBody DeveloperRequest request) {
        return ResponseEntity.ok(developerService.updateDeveloper(id, request));
    }
}
