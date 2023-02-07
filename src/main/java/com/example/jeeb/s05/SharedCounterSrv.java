/*
 * Introduction to Jakarta Enterprise Edition - Enterprise Bean
 * 
 * https://github.com/egalli64/jeeb
 */
package com.example.jeeb.s05;

import java.io.IOException;

import org.jboss.logging.Logger;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * A servlet using a non-session-scoped SFSB
 */
@SuppressWarnings("serial")
@WebServlet("/s05/sharedCount")
public class SharedCounterSrv extends HttpServlet {
    private static final Logger log = Logger.getLogger(SharedCounterSrv.class);

    @Inject
    SharedCounterSFSB service;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.info("");
        response.getWriter().println("Counter is: " + service.increase());
    }
}
