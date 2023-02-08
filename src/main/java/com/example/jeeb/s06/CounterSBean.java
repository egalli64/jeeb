/*
 * Introduction to Jakarta Enterprise Edition - Enterprise Bean
 * 
 * https://github.com/egalli64/jeeb
 */
package com.example.jeeb.s06;

import org.jboss.logging.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.DependsOn;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

/**
 * Singleton EJB initialized at startup, depending on another singleton
 */
@Singleton
@Startup
@DependsOn("InceptionSBean")
public class CounterSBean {
    private static final Logger log = Logger.getLogger(CounterSBean.class);

    private int counter = 0;

    @PostConstruct
    public void init() {
        log.info("initialized");
    }

    /**
     * Change the bean status
     * 
     * @return its new value
     */
    public int increase() {
        log.info("counter was " + counter);
        counter += 1;
        return counter;
    }
}
