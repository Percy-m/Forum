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

    private SnowflakeIdUtil snowflakeIdUtil;

    @Autowired
    public void setAccountRepository(AccountRepository accountRepository) {

        this.accountRepository = accountRepository;
    }

    @Autowired
    public void setTopicRepository(TopicRepository topicRepository) {

        this.topicRepository = topicRepository;
    }

    @Autowired
    public void setSnowflakeIdUtil(SnowflakeIdUtil snowflakeIdUtil) {

        this.snowflakeIdUtil = snowflakeIdUtil;
    }

    @Override
    public void addTopic(TopicDTO topicDTO, String accountId) {

        Account account = accountRepository.findById(UUID.fromString(accountId))
                .orElseThrow(() -> BusinessException.UserNotFound);
        topicRepository.save(Topic.of(
                snowflakeIdUtil.nextId(),
                topicDTO.getTitle(),
                account,
                topicDTO.getContent(),
                LocalDateTime.now()));
    }
}
