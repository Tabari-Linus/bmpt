package lii.buildmansterprojectmanagerapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lii.buildmansterprojectmanagerapi.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskRequest {
    @NotBlank
    private String title;

    private String description;

    @NotBlank
    private TaskStatus status;

    @NotNull
    private LocalDate dueDate;

    @NotNull
    private Long projectId;

    private Long developerId;
}
