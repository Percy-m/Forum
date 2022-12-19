package com.enterprise.forum.dto;

import com.enterprise.forum.domain.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * @author Jiayi Zhu
 * 2022/12/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountAuthDTO {

    private String username;

    private String password;

    public Account toAccount(PasswordEncoder passwordEncoder) {

        return Account.commonUser(username, passwordEncoder.encode(password));
    }
}
