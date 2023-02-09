/*
 * Introduction to Jakarta Enterprise Edition - Enterprise Bean
 * 
 * https://github.com/egalli64/jeeb
 */
package com.example.jeeb.s11;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.jboss.logging.Logger;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * A servlet using an EJB with asynchronous methods
 */
@SuppressWarnings("serial")
@WebServlet("/s11/longRunning")
public class LongRunning extends HttpServlet {
    private static final Logger log = Logger.getLogger(LongRunning.class);

    @Inject
    LongRunningBean service;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.info("enter");

        String type = request.getParameter("type");
        if (type == null || type.isBlank()) {
            type = "SYNC";
        }
        String result = switch (type) {
        case "FF" -> { // FF: Fire and Forget
            service.fireAndForget();
            yield "Fired and forgetted";
        }
        case "FFE" -> { // FFE: Fire and Forget w/ exception
            service.fireAndForgetEx();
            yield "Fired and forgetted - exception";
        }
        case "FC" -> { // FC: Fire and Check later
            try {
                Future<String> future = service.fireAndCheck();

                log.info("simulating a long job FC");
                Thread.sleep(800);

                yield future.get();
            } catch (InterruptedException | ExecutionException e) {
                log.info("Fire and check failure", e);
                yield "Something went wrong";
            }
        }
        case "FCE" -> { // FCE: Fire and Check later w/ exception
            try {
                Future<String> future = service.fireAndCheckEx();

                log.info("simulating a long job FCE");
                Thread.sleep(800);
                log.info("Now get the future");
                yield future.get();
            } catch (InterruptedException | ExecutionException e) {
                log.error("Fire and check failure: " + e.getMessage());
                yield "Something went wrong";
            }
        }
        case "FCC" -> { // FCC: Fire and Check but cancelled before checking
            try {
                Future<String> future = service.fireAndCheckCancel();

                log.info("simulating a long job FCC");
                Thread.sleep(500);
                log.info("Changed my mind");
                future.cancel(true);

                if (future.isCancelled()) {
                    log.info("Don't wait for future");
                    yield "Alternative future";
                } else {
                    log.info("Wait for cancelled future");
                    yield future.get();
                }
            } catch (InterruptedException | ExecutionException e) {
                log.error("Fire and check failure: " + e.getMessage());
                yield "Something went wrong";
            }
        }
        // by default, SYNC: synchronous call
        default -> service.synchronous();
        };

        response.getWriter().println(result);
        log.info("exit");
    }
}
