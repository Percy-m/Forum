package com.enterprise.forum.constant;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author Jiayi Zhu
 * 2023/1/6
 */
public class FormatConstant {

    @Value("${spring.jackson.date-format}")
    public static String DATE_FORMATTER_PATTERN;

}
