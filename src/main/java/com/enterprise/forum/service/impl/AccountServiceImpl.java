package com.enterprise.forum.service.impl;

import com.enterprise.forum.domain.Account;
import com.enterprise.forum.repository.AccountRepository;
import com.enterprise.forum.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Jiayi Zhu
 * 2022/12/17
 */
@Service
public class AccountServiceImpl implements AccountService {

    private static final String USERNAME_NOT_FOUND_MESSAGE = "Username not exist";

    private AccountRepository accountRepository;

    @Autowired
    public void setAccountRepository(AccountRepository accountRepository) {

        this.accountRepository = accountRepository;
    }

    @Override
    public void addAccount(Account account) throws Exception {

        Optional<Account> oldAccount = accountRepository.findAccountByUsername(account.getUsername());
        if (oldAccount.isPresent()) {
            throw new Exception("");
        }
        accountRepository.save(account);
    }

    @Override
    public void updateUsername(UUID id, String newUsername) throws Exception {

        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isEmpty()) {
            throw new Exception();
        }
        Account account = optionalAccount.get();
        account.setUsername(newUsername);
        accountRepository.save(account);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Account> account = accountRepository.findAccountByUsername(username);
        if (account.isPresent()) {
            return account.get();
        }
        throw new UsernameNotFoundException(USERNAME_NOT_FOUND_MESSAGE);
    }
}
