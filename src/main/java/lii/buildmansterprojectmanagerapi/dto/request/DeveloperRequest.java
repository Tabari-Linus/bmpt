package lii.buildmansterprojectmanagerapi.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeveloperRequest {
    @NotBlank
    private String developerName;

    @NotBlank
    @Email
    private String developerEmail;

    private Set<String> developerSkills;
}
