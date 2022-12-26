package com.enterprise.forum.service.impl;

import com.enterprise.forum.domain.Topic;
import com.enterprise.forum.repository.TopicRepository;
import com.enterprise.forum.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Jiayi Zhu
 * 12/24/2022
 */
@Service
public class TopicServiceImpl implements TopicService {

    private TopicRepository topicRepository;

    @Autowired
    public void setTopicRepository(TopicRepository topicRepository) {

        this.topicRepository = topicRepository;
    }


//    @Override
//    public void addTopic(Topic topic, UUID accountId) {
//
//        Optional<Topic> optionalTopic = topicRepository.findTopicsByAccount_Id()
//    }
}
