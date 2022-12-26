package com.enterprise.forum.controller.topics;

import com.enterprise.forum.annotation.CurrentAccount;
import com.enterprise.forum.dto.AccountUserDetails;
import com.enterprise.forum.dto.TopicDTO;
import com.enterprise.forum.exception.BusinessException;
import com.enterprise.forum.service.TopicService;
import com.enterprise.forum.vo.CommonVO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jiayi Zhu
 * 12/26/2022
 */
@RestController
@RequestMapping("/api/topics")
public class TopicController {

    private TopicService topicService;

    @Autowired
    public void setTopicService(TopicService topicService) {

        this.topicService = topicService;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("")
    public CommonVO createTopic(
            @Valid @RequestBody TopicDTO param,
            @CurrentAccount AccountUserDetails account) {

        try {
            topicService.addTopic(param, account.getId());
        } catch (BusinessException e) {
            return CommonVO.error(e.getMessage());
        }
        return CommonVO.ok();
    }
}
