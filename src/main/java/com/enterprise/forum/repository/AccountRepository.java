package com.enterprise.forum.repository;

import com.enterprise.forum.domain.Account;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Jiayi Zhu
 * 2022/12/14
 */
@Repository
public interface AccountRepository extends
        ListCrudRepository<Account, UUID>, ListPagingAndSortingRepository<Account, UUID>
{

    Optional<Account> findAccountByUsername(String username);

    boolean existsAccountByUsername(String username);

}
