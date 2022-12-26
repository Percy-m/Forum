package com.enterprise.forum.service;

import com.enterprise.forum.dto.TopicDTO;

/**
 * @author Jiayi Zhu
 * 12/24/2022
 */
public interface TopicService {

    void addTopic(TopicDTO topicDTO, String accountId);
}
