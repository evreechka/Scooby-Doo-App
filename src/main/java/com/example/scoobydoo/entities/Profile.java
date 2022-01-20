package com.example.scoobydoo.entities;

import com.example.scoobydoo.entities.enums.SystemRoleType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "PROFILE")
public class Profile implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private long id;


    @NotBlank(message = "username can't be blank")
    @Column(name = "username")
    private String username;

    @NotBlank(message = "password can't be blank")
    @Column(name = "password")
    private String password;

    @Column(name = "profile_photo")
    private String photo;

    //TODO
    @OneToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "investigator_id")
    private Investigator user;

    public boolean isAdmin() {
        return getUser().getCharacter().getRole().name().equals(SystemRoleType.ADMIN.name());
    }
    public boolean isSheriff() {
        return getUser().getCharacter().getRole().name().equals(SystemRoleType.SHERIFF.name());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
