package lii.buildmansterprojectmanagerapi.service;


import jakarta.transaction.Transactional;
import lii.buildmansterprojectmanagerapi.dto.request.DeveloperRequest;
import lii.buildmansterprojectmanagerapi.dto.response.DeveloperResponse;
import lii.buildmansterprojectmanagerapi.entity.jpa.Developer;
import lii.buildmansterprojectmanagerapi.exception.DeveloperAlreadyExistsException;
import lii.buildmansterprojectmanagerapi.exception.DeveloperNotFoundException;
import lii.buildmansterprojectmanagerapi.exception.ResourceNotFoundException;
import lii.buildmansterprojectmanagerapi.repository.jpa.DeveloperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeveloperService {

    private final DeveloperRepository developerRepository;

    public DeveloperResponse createDeveloper(DeveloperRequest request) {
        if (developerRepository.existsByDeveloperEmail(request.getDeveloperEmail())) {
            throw new DeveloperAlreadyExistsException(request.getDeveloperEmail());
        }
        Developer developer = Developer.builder()
                .developerName(request.getDeveloperName())
                .developerEmail(request.getDeveloperEmail())
                .developerSkills(request.getDeveloperSkills())
                .build();

        Developer saved = developerRepository.save(developer);

        return DeveloperResponse.builder()
                .id(saved.getId())
                .developerName(saved.getDeveloperName())
                .developerEmail(saved.getDeveloperEmail())
                .developerSkills(saved.getDeveloperSkills())
                .build();
    }

    public List<DeveloperResponse> findAll() {
        return developerRepository.findAll().stream()
                .map(d -> DeveloperResponse.builder()
                        .id(d.getId())
                        .developerName(d.getDeveloperName())
                        .developerEmail(d.getDeveloperEmail())
                        .developerSkills(d.getDeveloperSkills())
                        .build())
                .toList();
    }

    public DeveloperResponse findById(Long id) {
        Developer dev = developerRepository.findById(id)
                .orElseThrow(() -> new DeveloperNotFoundException(id));
        return DeveloperResponse.builder()
                .id(dev.getId())
                .developerName(dev.getDeveloperName())
                .developerEmail(dev.getDeveloperEmail())
                .developerSkills(dev.getDeveloperSkills())
                .build();
    }

    @Transactional
    public void delete(Long id) {

        if (!developerRepository.existsById(id)) {
            throw new DeveloperNotFoundException(id);
        }
        developerRepository.deleteById(id);
    }

    public DeveloperResponse updateDeveloper(Long id, DeveloperRequest request) {
        Developer developer = developerRepository.findById(id)
                .orElseThrow(() -> new DeveloperNotFoundException(id));

        developer.setDeveloperName(request.getDeveloperName());
        developer.setDeveloperEmail(request.getDeveloperEmail());
        developer.setDeveloperSkills(request.getDeveloperSkills());

        Developer updated = developerRepository.save(developer);
        return DeveloperResponse.builder()
                .id(updated.getId())
                .developerName(updated.getDeveloperName())
                .developerEmail(updated.getDeveloperEmail())
                .developerSkills(updated.getDeveloperSkills())
                .build();
    }

    public Page<DeveloperResponse> getAllDevelopers(Pageable pageable) {
        return developerRepository.findAll(pageable)
                .map(dev -> DeveloperResponse.builder()
                        .id(dev.getId())
                        .developerName(dev.getDeveloperName())
                        .developerEmail(dev.getDeveloperEmail())
                        .developerSkills(dev.getDeveloperSkills())
                        .build());
    }


}
