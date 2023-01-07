package com.enterprise.forum.utils;

import com.enterprise.forum.exception.ForumException;
import org.slf4j.Logger;

/**
 * @author Jiayi Zhu
 * 2023/1/7
 */
public class LogUtil {

    public static void logError(Logger log, ForumException e) {

        String builder = "Exception: " + e.getClass().getName() + ", " + "Status: " + e.getStatus().toString() + ", " +
                "Message: " + e.getMessage();
        log.error(builder);
    }

}
