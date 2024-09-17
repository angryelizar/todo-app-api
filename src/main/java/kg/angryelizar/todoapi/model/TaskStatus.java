package kg.angryelizar.todoapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Table(name = "TASK_STATUSES")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;

    @OneToMany(mappedBy = "status")
    private List<Task> tasks;
}
