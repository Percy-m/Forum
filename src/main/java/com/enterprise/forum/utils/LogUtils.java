package com.enterprise.forum.utils;

import com.enterprise.forum.exception.ForumException;
import org.slf4j.Logger;

/**
 * @author Jiayi Zhu
 * 2023/1/7
 */
public class LogUtils {

    public static void logError(Logger log, Exception e) {

        String errorMsg;
        if (e instanceof ForumException fe) {
            errorMsg
                    = "Exception: " + fe.getClass().getName() + ", "
                    + "Status: " + fe.getStatus().toString() + ", "
                    + "Message: " + fe.getMessage();
        }
        else {
            errorMsg = formattedExceptionMessage(e);
        }
        log.error(errorMsg);
    }

    public static String formattedExceptionMessage(Exception e) {

        return "Exception: " + e.getClass().getName() + ", "
                + "Message: " + e.getMessage();
    }

}
