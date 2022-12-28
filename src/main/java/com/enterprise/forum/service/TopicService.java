package com.enterprise.forum.service;

import com.enterprise.forum.domain.Topic;
import com.enterprise.forum.dto.TopicDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Jiayi Zhu
 * 12/24/2022
 */
public interface TopicService {

    void addTopic(TopicDTO topicDTO, String accountId);

    Page<Topic> getAllTopics(Pageable pageable);

}
