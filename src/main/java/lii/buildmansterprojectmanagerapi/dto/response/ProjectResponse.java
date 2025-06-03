package lii.buildmansterprojectmanagerapi.dto.response;

import lii.buildmansterprojectmanagerapi.enums.ProjectStatus;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ProjectResponse(
        Long id,
        String title,
        String description,
        LocalDateTime deadline,
        ProjectStatus status
) {}

