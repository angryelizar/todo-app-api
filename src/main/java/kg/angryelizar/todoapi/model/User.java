package kg.angryelizar.todoapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Builder
@Table(name = "USERS")
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    private String email;
    private String username;
    private String password;
    private Boolean enabled;
    @JoinColumn(name = "authority_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Authority authority;

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private List<Task> tasks;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(authority.getAuthority()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
