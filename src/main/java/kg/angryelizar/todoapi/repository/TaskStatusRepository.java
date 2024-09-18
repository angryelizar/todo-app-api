package kg.angryelizar.todoapi.repository;

import kg.angryelizar.todoapi.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskStatusRepository extends JpaRepository<TaskStatus, Long> {
    TaskStatus findByStatus(String status);
    Boolean existsByStatus(String status);
}
