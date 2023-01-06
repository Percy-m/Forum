package com.enterprise.forum.service.impl;

import com.enterprise.forum.domain.Account;
import com.enterprise.forum.domain.Topic;
import com.enterprise.forum.dto.TopicDTO;
import com.enterprise.forum.exception.BusinessException;
import com.enterprise.forum.repository.AccountRepository;
import com.enterprise.forum.repository.TopicRepository;
import com.enterprise.forum.service.TopicService;
import com.enterprise.forum.utils.SnowflakeIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Jiayi Zhu
 * 12/24/2022
 */
@Service
public class TopicServiceImpl implements TopicService {

    private AccountRepository accountRepository;

    private TopicRepository topicRepository;

    @Autowired
    public void setAccountRepository(AccountRepository accountRepository) {

        this.accountRepository = accountRepository;
    }

    @Autowired
    public void setTopicRepository(TopicRepository topicRepository) {

        this.topicRepository = topicRepository;
    }


    @Override
    public void addTopic(TopicDTO topicDTO, String accountId) throws BusinessException {

        Account account = accountRepository.findById(UUID.fromString(accountId))
                .orElseThrow(() -> BusinessException.UserNotFound);
        topicRepository.save(Topic.of(
                SnowflakeIdUtil.nextId(),
                topicDTO.getTitle(),
                account,
                topicDTO.getContent(),
                LocalDateTime.now()));
    }

    @Override
    public Page<Topic> getAllTopics(Pageable pageable) {

        return topicRepository.getAllBy(pageable);
    }


    private Topic validateTopicOwner(Long topicId, String username) throws BusinessException {

        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> BusinessException.TopicNotFound);

        if (topic.getAccount().getUsername().equals(username)) {
            return topic;
        }
        throw BusinessException.ActionNotAllowed;
    }

    @Override
    public void updateTopic(Long topicId, TopicDTO topicDTO, String username) throws BusinessException {

        Topic topic = validateTopicOwner(topicId, username);

        topic.setTitle(topicDTO.getTitle());
        topic.setContent(topicDTO.getContent());

        topicRepository.save(topic);
    }
}
