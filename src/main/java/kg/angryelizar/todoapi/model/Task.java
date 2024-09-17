package kg.angryelizar.todoapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@Table(name = "TASKS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @JoinColumn(name = "author")
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private User author;
    @JoinColumn(name = "status_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private TaskStatus status;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;

}
