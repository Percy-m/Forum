package com.enterprise.forum.service.impl;

import com.enterprise.forum.domain.Account;
import com.enterprise.forum.dto.AccountAuthDTO;
import com.enterprise.forum.dto.AccountUserDetails;
import com.enterprise.forum.dto.UsernameChangeDTO;
import com.enterprise.forum.exception.BusinessException;
import com.enterprise.forum.repository.AccountRepository;
import com.enterprise.forum.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.function.Function;

/**
 * @author Jiayi Zhu
 * 2022/12/17
 */
@Service
public class AccountServiceImpl implements AccountService {

    private static final String USERNAME_NOT_FOUND_MESSAGE = "用户名不存在";

    private AccountRepository accountRepository;

    @Autowired
    public void setAccountRepository(AccountRepository accountRepository) {

        this.accountRepository = accountRepository;
    }

    @Override
    public void addAccount(AccountAuthDTO accountAuthDTO, Function<String, String> encode) throws BusinessException {

        if (accountRepository.existsAccountByUsername(accountAuthDTO.getUsername())) {
            throw BusinessException.UsernameExisted;
        }

        Account account = accountAuthDTO.toAccount(encode);
        accountRepository.save(account);
    }

    @Override
    public void updateUsername(AccountUserDetails accountUserDetails,
                               UsernameChangeDTO usernameChangeDTO) throws BusinessException {

        UUID id = UUID.fromString(accountUserDetails.getId());
        String newUsername = usernameChangeDTO.getNewUsername();

        Account account = accountRepository.findById(id)
                .orElseThrow(() -> BusinessException.UserNotFound);
        if (accountRepository.existsAccountByUsername(newUsername)) {
            throw BusinessException.UsernameExisted;
        }

        account.setUsername(newUsername);
        accountRepository.save(account);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = accountRepository.findAccountByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(USERNAME_NOT_FOUND_MESSAGE));

        return AccountUserDetails.fromAccount(account);
    }
}
