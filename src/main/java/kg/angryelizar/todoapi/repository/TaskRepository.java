package kg.angryelizar.todoapi.repository;

import kg.angryelizar.todoapi.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
