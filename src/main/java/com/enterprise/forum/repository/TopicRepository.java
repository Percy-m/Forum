package com.enterprise.forum.repository;

import com.enterprise.forum.domain.Topic;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Jiayi Zhu
 * 12/24/2022
 */
@Repository
public interface TopicRepository extends
        ListCrudRepository<Topic, Long>, ListPagingAndSortingRepository<Topic, Long> {

}
