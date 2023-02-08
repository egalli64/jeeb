/*
 * Introduction to Jakarta Enterprise Edition - Enterprise Bean
 * 
 * https://github.com/egalli64/jeeb
 */
package com.example.jeeb.s06;

import java.io.IOException;

import org.jboss.logging.Logger;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * A servlet using a couple of singleton EJB
 */
@SuppressWarnings("serial")
@WebServlet("/s06/count")
public class CounterSrv extends HttpServlet {
    private static final Logger log = Logger.getLogger(CounterSrv.class);

    @Inject
    CounterSBean counterSvc;

    @Inject
    GreeterSBean greetSvc;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.info(greetSvc.greeting());
        response.getWriter().println("Counter is: " + counterSvc.increase());
    }
}
