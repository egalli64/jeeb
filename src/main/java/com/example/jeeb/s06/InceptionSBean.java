/*
 * Introduction to Jakarta Enterprise Edition - Enterprise Bean
 * 
 * https://github.com/egalli64/jeeb
 */
package com.example.jeeb.s06;

import org.jboss.logging.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

/**
 * A singleton EJB generated at startup
 */
@Singleton
@Startup
public class InceptionSBean {
    private static final Logger log = Logger.getLogger(InceptionSBean.class);

    @PostConstruct
    public void init() {
        log.info("initialized");
    }
}
