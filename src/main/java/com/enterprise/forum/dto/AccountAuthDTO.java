package com.enterprise.forum.dto;

import com.enterprise.forum.domain.Account;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.function.Function;


/**
 * @author Jiayi Zhu
 * 2022/12/17
 */
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountAuthDTO {

    private String username;

    private String password;

    public Account toAccount(Function<String, String> encode) {

        return Account.commonUser(username, password, encode);
    }
}
