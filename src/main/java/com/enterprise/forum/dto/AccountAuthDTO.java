package com.enterprise.forum.dto;

import com.enterprise.forum.domain.Account;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * @author Jiayi Zhu
 * 2022/12/17
 */
@Data
public class AccountAuthDTO {

    @NotBlank
    private String username;

    @NotBlank
    @Size(min = 8)
    private String password;

    public Account toAccount(PasswordEncoder passwordEncoder) {

        return Account.commonUser(username, passwordEncoder.encode(password));
    }
}
