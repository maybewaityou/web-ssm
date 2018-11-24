package com.meepwn.ssm.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtils {

    private static final Logger logger = LoggerFactory.getLogger(LogUtils.class);

    public static void d(String msg) {
        logger.debug(msg);
    }

    public static void e(String msg) {
        logger.error(msg);
    }

    private static String defaultTag() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement log = stackTrace[1];
        String tag = null;
        for (int i = 1; i < stackTrace.length; i++) {
            StackTraceElement e = stackTrace[i];
            if (!e.getClassName().equals(log.getClassName())) {
                tag = e.getClassName();
                break;
            }
        }
        if (tag == null) {
            tag = log.getClassName();
        }
        return tag;
    }

}
