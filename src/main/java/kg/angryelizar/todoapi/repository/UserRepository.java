package kg.angryelizar.todoapi.repository;

import kg.angryelizar.todoapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
