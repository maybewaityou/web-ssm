package com.meepwn.ssm.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtils {

    private static final Logger logger = LoggerFactory.getLogger(LogUtils.class);

    public static void d(String msg) {
        if (logger.isDebugEnabled()) {
            logger.debug(msg);
        }
    }

    public static void d(String format, Object arg) {
        if (logger.isDebugEnabled()) {
            logger.debug(format, arg);
        }
    }

    public static void i(String msg) {
        if (logger.isInfoEnabled()) {
            logger.info(msg);
        }
    }

    public static void i(String format, Object arg) {
        if (logger.isInfoEnabled()) {
            logger.info(format, arg);
        }
    }

    public static void e(String msg) {
        if (logger.isErrorEnabled()) {
            logger.error(msg);
        }
    }

    public static void e(String format, Object arg) {
        if (logger.isErrorEnabled()) {
            logger.error(format, arg);
        }
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
