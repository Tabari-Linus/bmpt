package lii.buildmansterprojectmanagerapi.dto.response;

import lombok.Builder;

import java.util.Set;

@Builder
public record DeveloperResponse(Long id, String developerName, String developerEmail, Set<String> developerSkills) {
}
