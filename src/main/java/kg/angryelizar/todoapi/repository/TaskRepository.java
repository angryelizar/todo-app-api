package kg.angryelizar.todoapi.repository;

import kg.angryelizar.todoapi.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query(value = "select * from TASKS where AUTHOR = :authorEmail and STATUS_ID != :excludeStatusId", nativeQuery = true)
    List<Task> findActiveTaskByAuthor(String authorEmail, Long excludeStatusId);
}
