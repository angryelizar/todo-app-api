package kg.angryelizar.todoapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Table(name = "AUTHORITIES")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authority;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "authority")
    private List<User> users;

}
