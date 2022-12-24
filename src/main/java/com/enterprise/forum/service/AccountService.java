package com.enterprise.forum.service;

import com.enterprise.forum.dto.AccountAuthDTO;
import com.enterprise.forum.dto.AccountUserDetails;
import com.enterprise.forum.dto.UsernameChangeDTO;
import com.enterprise.forum.exception.BusinessException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.function.Function;

/**
 * @author Jiayi Zhu
 * 2022/12/17
 */
public interface AccountService extends UserDetailsService{

    void addAccount(AccountAuthDTO accountAuthDTO, Function<String, String> encode) throws BusinessException;

    void updateUsername(AccountUserDetails accountUserDetails,
                        UsernameChangeDTO usernameChangeDTO) throws BusinessException;



}
