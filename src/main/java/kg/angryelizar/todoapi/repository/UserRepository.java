package kg.angryelizar.todoapi.repository;

import kg.angryelizar.todoapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> getByEmail(String email);
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
}
