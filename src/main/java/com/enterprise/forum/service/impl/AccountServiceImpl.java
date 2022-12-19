package com.enterprise.forum.service.impl;

import com.enterprise.forum.domain.Account;
import com.enterprise.forum.repository.AccountRepository;
import com.enterprise.forum.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jiayi Zhu
 * 2022/12/17
 */
@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public void setAccountRepository(AccountRepository accountRepository) {

        this.accountRepository = accountRepository;
    }

    @Override
    public void addAccount(Account account) throws Exception {

        Account oldAccount = accountRepository.findAccountByUsername(account.getUsername());
        if (oldAccount != null) {
            throw new Exception("");
        }
        accountRepository.save(account);
    }
}
