/*
 * Introduction to Jakarta Enterprise Edition - Enterprise Bean
 * 
 * https://github.com/egalli64/jeeb
 */
package com.example.jeeb.s04;

import java.io.IOException;

import org.jboss.logging.Logger;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * A servlet using the Greeter service
 */
@SuppressWarnings("serial")
@WebServlet("/s04/greeter")
public class GreeterSrv extends HttpServlet {
    private static final Logger log = Logger.getLogger(GreeterSrv.class);

    /**
     * Being local, and not requiring any special feature, the Inject annotation suffices
     */
    @Inject
    private Greeter greeter;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        if (name == null || name.isBlank()) {
            name = "Guest";
            log.info("No name provided by user, using default");
        }
        response.getWriter().append(greeter.greetings(name));
    }
}
