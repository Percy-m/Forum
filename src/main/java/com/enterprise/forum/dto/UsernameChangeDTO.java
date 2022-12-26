package com.enterprise.forum.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author Jiayi Zhu
 * 2022/12/21
 */
@Data
public class UsernameChangeDTO {

    @NotBlank
    private String newUsername;
}
