package com.enterprise.forum.dto;

import com.enterprise.forum.domain.Account;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import static com.enterprise.forum.constant.RoleConstant.AUTHORITY_ADMIN;
import static com.enterprise.forum.constant.RoleConstant.AUTHORITY_USER;

/**
 * @author Jiayi Zhu
 * 12/23/2022
 */
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountUserDetails implements UserDetails {

    private static final GrantedAuthority GRANTED_AUTHORITY_USER
            = new SimpleGrantedAuthority(AUTHORITY_USER);

    private static final GrantedAuthority GRANTED_AUTHORITY_ADMIN
            = new SimpleGrantedAuthority(AUTHORITY_ADMIN);

    private String id;

    private String username;

    private String password;

    private Integer role;

    private Boolean locked;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if (role == 0) {
            return List.of(GRANTED_AUTHORITY_USER);
        }
        return List.of(
                GRANTED_AUTHORITY_USER,
                GRANTED_AUTHORITY_ADMIN);
    }

    @Override
    public String getPassword() {

        return password;
    }

    @Override
    public String getUsername() {

        return username;
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
     * Parse an {@link Account} object to {@link AccountUserDetails}
     *
     * @param account the account object
     * @return the dto object
     */
    public static AccountUserDetails fromAccount(Account account) {

        return new AccountUserDetails(
                account.getId().toString(),
                account.getUsername(),
                account.getPassword(),
                account.getRole(),
                account.getLocked());
    }
}
