package com.enterprise.forum.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author Jiayi Zhu
 * 12/26/2022
 */
@Data
public class TopicDTO {

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}
