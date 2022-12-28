package com.enterprise.forum.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author Jiayi Zhu
 * 12/27/2022
 */
@Data
public class PageDTO {

    @NotNull
    @Min(1)
    private Integer page;

    @NotNull
    @Min(1)
    private Integer pageSize;

}
