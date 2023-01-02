package com.enterprise.forum.repository;

import com.enterprise.forum.domain.security.RefreshToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Jiayi Zhu
 * 1/1/2023
 */
@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {

}
