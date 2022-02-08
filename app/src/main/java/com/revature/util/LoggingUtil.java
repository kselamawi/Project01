package com.revature.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class LoggingUtil {
    private Date date = new Date();
    private static Logger logger = LoggerFactory.getLogger(LoggingUtil.class);

    public void logConnection() { logger.info("Database connection at " + date);}

    public LoggingUtil() {
    }

    public void logSuccessfulQuery(String query){logger.info(query + "was successful");}

    public void logger(String info){
        logger.info(info);
    }

    public void queryLogger(String query){
        logger.info(query + " was just attempted");
    }

}