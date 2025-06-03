package lii.buildmansterprojectmanagerapi.repository.jpa;

import lii.buildmansterprojectmanagerapi.entity.jpa.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {
    boolean existsByDeveloperEmail(String name);
    Optional<Developer> findByDeveloperEmail(String email);
}
