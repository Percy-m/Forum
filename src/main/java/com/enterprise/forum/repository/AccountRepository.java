package com.enterprise.forum.repository;

import com.enterprise.forum.domain.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author Jiayi Zhu
 * 2022/12/14
 */
@Repository
public interface AccountRepository extends CrudRepository<Account, UUID> {

    <S extends Account> S findAccountByUsername(String username);


}
