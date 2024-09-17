package kg.angryelizar.todoapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@Table(name = "TASKS")
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @JoinColumn(name = "author")
    @ManyToOne(fetch = FetchType.LAZY)
    private User author;
    @JoinColumn(name = "status_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TaskStatus status;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;

}
