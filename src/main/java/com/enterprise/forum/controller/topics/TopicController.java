package com.enterprise.forum.controller.topics;

import com.enterprise.forum.annotation.CurrentAccount;
import com.enterprise.forum.dto.AccountUserDetails;
import com.enterprise.forum.dto.PageDTO;
import com.enterprise.forum.dto.TopicDTO;
import com.enterprise.forum.exception.BusinessException;
import com.enterprise.forum.service.TopicService;
import com.enterprise.forum.utils.PageTransformUtil;
import com.enterprise.forum.vo.CommonVO;
import com.enterprise.forum.vo.TopicVO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;

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
            return CommonVO.error(e.getCode(), e.getMessage());
        }
        return CommonVO.created();
    }

    @GetMapping()
    public CommonVO getTopics(@Valid PageDTO param) {

        var topicVOPage = PageTransformUtil.toViewPage(
                param,
                pageable -> topicService.getAllTopics(pageable),
                topic -> new TopicVO(
                        topic.getTitle(),
                        topic.getAccount().getUsername(),
                        topic.getContent(),
                        topic.getReplies(),
                        topic.getClicks(),
                        topic.getTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                );
        return CommonVO.ok(topicVOPage);
    }
}
