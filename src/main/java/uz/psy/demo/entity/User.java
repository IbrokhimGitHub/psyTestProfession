package uz.psy.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Entity(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private UUID uuid;

    @Size(min = 3, max = 50)
    @Column(nullable = false, length = 50)
    private String firstName;

    @Size(min = 3, max = 50)
    @Column(nullable = false, length = 50)
    private String lastName;

    private Integer age;

    private String phoneNumber;

    private String password;

    private Integer questionCounter=0;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updateAt;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true; //bu user blocklanmagan
    private boolean credentialsNonExpired = true; //bu user sroki o'tmagan
    private boolean enabled=true; //bu user yoniqmi

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getUsername() {
        return this.phoneNumber;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
