package lii.buildmansterprojectmanagerapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lii.buildmansterprojectmanagerapi.enums.ProjectStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ProjectRequest {

    @NotBlank
    private String title;

    private String description;

    @NotNull
    private LocalDateTime deadline;

    @NotNull(groups = ProjectStatus.class)
    private ProjectStatus status;
}
