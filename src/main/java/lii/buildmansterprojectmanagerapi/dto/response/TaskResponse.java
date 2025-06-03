package lii.buildmansterprojectmanagerapi.dto.response;

import lii.buildmansterprojectmanagerapi.enums.TaskStatus;
import lombok.Builder;

import java.time.LocalDate;


@Builder
public record TaskResponse(Long id,
                           String title,
                           String description,
                           TaskStatus status,
                           LocalDate dueDate,
                           Long projectId,
                           Long developerId) {
}
