package lii.buildmansterprojectmanagerapi.repository.jpa;

import lii.buildmansterprojectmanagerapi.entity.jpa.Task;
import lii.buildmansterprojectmanagerapi.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByProjectId(Long projectId);
    List<Task> findByDeveloperId(Long developerId);
    List<Task> findByStatus(TaskStatus status);
}
