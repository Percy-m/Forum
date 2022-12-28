package com.enterprise.forum.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Jiayi Zhu
 * 12/27/2022
 */
@Data
@AllArgsConstructor
public class TopicVO {

    private String title;

    private String owner;

    private String content;

    private Integer replies;

    private Integer clicks;

    private String time;


}
