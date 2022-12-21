package com.enterprise.forum.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * @author Jiayi Zhu
 * 2022/12/14
 */
@Entity
@Table(name="account",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "username")
})
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Account implements Serializable, UserDetails {

    public static final String ROLE_USER = "ROLE_USER";

    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    @Id
    @Column(name = "id",
            columnDefinition = "uuid",
            nullable = false)
    private UUID id;

    @Column(name = "username",
            columnDefinition = "character varying (20)",
            nullable = false)
    private String username;

    @Column(name = "password",
            columnDefinition = "character (60)",
            nullable = false)
    private String password;

    @Column(name = "role",
            columnDefinition = "smallint check (role in (0, 1))",
            nullable = false)
    private Integer role;

    @Column(name = "is_locked",
            columnDefinition = "boolean",
            nullable = false)
    private Boolean locked;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private List<Topic> topicList;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private List<Post> postList;

    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if (role == 0)
            return List.of(new SimpleGrantedAuthority(ROLE_USER));
        else return List.of(
                new SimpleGrantedAuthority(ROLE_USER),
                new SimpleGrantedAuthority(ROLE_ADMIN));
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }

    /**
     * Returns a common user account instead of an admin account
     *
     * @param username the username
     * @param password the password
     * @return a common user account
     */
    public static Account commonUser(String username, String password) {

        return new Account(
                UUID.randomUUID(),
                username,
                password,
                0,
                false,
                new ArrayList<>(),
                new ArrayList<>());
    }
}
