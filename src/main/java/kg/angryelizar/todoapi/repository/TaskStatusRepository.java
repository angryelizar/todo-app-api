package kg.angryelizar.todoapi.repository;

import kg.angryelizar.todoapi.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskStatusRepository extends JpaRepository<TaskStatus, Long> {
}
