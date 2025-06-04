package lii.buildmansterprojectmanagerapi.entity.jpa;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Developer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String developerName;

    @Email
    @Column(unique = true)
    @NotBlank
    private String developerEmail;

    @ElementCollection
    @NotEmpty
    private Set<String> developerSkills;

    @OneToMany(mappedBy = "developer")
    private List<Task> developerTasks;
}
