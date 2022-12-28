package com.enterprise.forum.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author Jiayi Zhu
 * 12/28/2022
 */
@Data
@AllArgsConstructor
public class PageVO<T> {

    List<T> content;

    /**
     * the number of current page
     */
    Integer number;

    /**
     * the size of the page
     */
    Integer size;

    /**
     * the number of total pages
     */
    Integer total;

    Boolean hasNext;

    Boolean hasPrev;

    Boolean isFirst;

    Boolean isLast;

}
