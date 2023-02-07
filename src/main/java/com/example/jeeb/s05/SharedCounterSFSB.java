/*
 * Introduction to Jakarta Enterprise Edition - Enterprise Bean
 * 
 * https://github.com/egalli64/jeeb
 */
package com.example.jeeb.s05;

import org.jboss.logging.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.ejb.Local;
import jakarta.ejb.Stateful;

/**
 * A Local Stateful Session Bean
 * 
 * Not being session scoped, a unique bean is shared by all the conversations
 */
@Stateful
@Local
public class SharedCounterSFSB {
    private static final Logger log = Logger.getLogger(SharedCounterSFSB.class);

    private int counter = 0;

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

    @PostConstruct
    public void init() {
        log.info("initialized");
    }

    @PreDestroy
    public void exit() {
        log.info("terminated with counter: " + counter);
    }
}
