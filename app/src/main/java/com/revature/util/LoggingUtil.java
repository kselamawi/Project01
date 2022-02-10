package com.revature.util;
// Import log4j classes.

import org.apache.log4j.Logger;

import java.util.Date;

public class LoggingUtil {

    private Date date = new Date();

    public static Logger logger = Logger.getLogger(LoggingUtil.class);
    Logger root = Logger.getRootLogger();


}