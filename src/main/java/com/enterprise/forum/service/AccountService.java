package com.enterprise.forum.service;

import com.enterprise.forum.domain.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

/**
 * @author Jiayi Zhu
 * 2022/12/17
 */
public interface AccountService extends UserDetailsService{

    void addAccount(Account account) throws Exception;

    void updateUsername(UUID id, String newUsername) throws Exception;



}
