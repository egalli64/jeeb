/*
 * Introduction to Jakarta Enterprise Edition - Enterprise Bean
 * 
 * https://github.com/egalli64/jeeb
 */
package com.example.jeeb.s06;

import org.jboss.logging.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;

/**
 * A simple singleton EJB, created on its first access
 */
@Singleton
public class GreeterSBean {
    private static final Logger log = Logger.getLogger(GreeterSBean.class);

    @PostConstruct
    public void init() {
        log.info("initialized");
    }

    /**
     * Generate a greeting message
     * 
     * @return a greeting message
     */
    public String greeting() {
        log.info("greeting");
        return "hello from singleton";
    }
}
