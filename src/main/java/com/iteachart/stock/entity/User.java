package com.iteachart.stock.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

@Entity
@Table(name = "User")
@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "is_blocked")
    private Boolean isBlocked;

    @Column(name = "subscribe_expire")
    private LocalDate subscribeExpireDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_role", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    private Role role;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_subscriber", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    private Subscribe subscribe;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<>(){
            {
                add(getRole());
            }
        };
    }

    @Override
    public String getUsername() {
        return getEmail();
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
