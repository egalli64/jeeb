/*
 * Introduction to Jakarta Enterprise Edition - Enterprise Bean
 * 
 * https://github.com/egalli64/jeeb
 */
package com.example.jeeb.s11;

import java.util.concurrent.Future;

import org.jboss.logging.Logger;

import jakarta.annotation.Resource;
import jakarta.ejb.AsyncResult;
import jakarta.ejb.Asynchronous;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;

/**
 * A simple SLSB with asynchronous methods
 */
@Stateless
public class LongRunningBean {
    private static final Logger log = Logger.getLogger(LongRunningBean.class);

    /**
     * Required for checking if an asynchronous job has been canceled
     */
    @Resource
    private SessionContext context;

    /**
     * Simple asynchronous call
     */
    @Asynchronous
    public void fireAndForget() {
        try {
            log.info("Simulating a long job");
            Thread.sleep(1000);
            log.info("Job completed");
        } catch (InterruptedException ie) {
            log.warn("Job interrupted", ie);
        }
    }

    /**
     * Simple asynchronous call, with exception
     */
    @Asynchronous
    public void fireAndForgetEx() {
        try {
            log.info("Simulating a long job that goes wrong");
            Thread.sleep(1000);
            throw new IllegalStateException("Boom!");
        } catch (InterruptedException ie) {
            log.warn("Job interrupted", ie);
        } finally {
            log.trace("exit");
        }
    }

    /**
     * No Asynchronous annotation, standard behavior
     * 
     * @return the job result
     */
    public String synchronous() {
        try {
            log.info("Simulating a long job");
            Thread.sleep(1000);
            log.info("Job completed");
        } catch (InterruptedException ie) {
            log.warn("Job interrupted", ie);
        }
        return "synchronous";
    }

    /**
     * Asynchronous that should return something
     * 
     * @return the job result is wrapped in a Future
     */
    @Asynchronous
    public Future<String> fireAndCheck() {
        try {
            log.info("Simulating a long job");
            Thread.sleep(1000);
            log.info("Job completed");
        } catch (InterruptedException ie) {
            log.warn("Job interrupted", ie);
        }
        return new AsyncResult<String>("Future result");
    }

    /**
     * Asynchronous that should return something, but an exception gets into the way
     * 
     * @return the job result is wrapped in a Future
     */
    @Asynchronous
    public Future<String> fireAndCheckEx() {
        try {
            log.info("Simulating a long job");
            Thread.sleep(1000);
            throw new IllegalStateException("Boom!");
        } catch (InterruptedException ie) {
            log.warn("Job interrupted", ie);
        }
        return new AsyncResult<String>("Never reached");
    }

    /**
     * Asynchronous that should return something, but the caller cancel it
     * 
     * @return the job result is wrapped in a Future
     */
    @Asynchronous
    public Future<String> fireAndCheckCancel() {
        try {
            log.info("Simulating a long job");
            for (int i = 0; i < 10; i++) {
                Thread.sleep(200);
                log.info("working " + i);
                if (context.wasCancelCalled()) {
                    return new AsyncResult<String>("Future cancelled");
                }
            }
        } catch (InterruptedException ie) {
            log.warn("Job interrupted", ie);
        }
        return new AsyncResult<String>("Future result");
    }
}
