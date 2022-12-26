package com.enterprise.forum.repository;

import com.enterprise.forum.domain.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author Jiayi Zhu
 * 12/24/2022
 */
@Repository
public interface TopicRepository extends
        ListCrudRepository<Topic, UUID>, ListPagingAndSortingRepository<Topic, UUID> {

    List<Topic> findTopicsByAccountId(UUID accountId);

    Page<Topic> findTopicsByAccountId(UUID accountId, Pageable pageable);
}
