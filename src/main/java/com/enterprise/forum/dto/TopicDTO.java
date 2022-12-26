package com.enterprise.forum.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Jiayi Zhu
 * 12/24/2022
 */
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TopicDTO {

    private String id;

    private String title;

    private String owner;

    private String content;

    private int clicks;

    private LocalDateTime time;

}
