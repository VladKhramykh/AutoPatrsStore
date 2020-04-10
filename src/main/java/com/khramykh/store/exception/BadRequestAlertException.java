package com.khramykh.store.exception;


import org.apache.log4j.Logger;

public class BadRequestAlertException extends RuntimeException{

    private static final Logger logger = Logger.getLogger(BadRequestAlertException.class);

    public BadRequestAlertException(String message) {
        logger.debug(message);
    }
}
