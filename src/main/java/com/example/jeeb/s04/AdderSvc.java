/*
 * Introduction to Jakarta Enterprise Edition - Enterprise Bean
 * 
 * https://github.com/egalli64/jeeb
 */
package com.example.jeeb.s04;

import java.io.IOException;

import org.jboss.logging.Logger;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * A servlet using the Adder service
 */
@SuppressWarnings("serial")
@WebServlet("/s04/adder")
public class AdderSvc extends HttpServlet {
    private static final Logger log = Logger.getLogger(AdderSvc.class);

    /**
     * There is only one bean implementing the Adder interface, so it is enough to identify it
     */
    @EJB
    Adder service;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.info("");

        String paramLeft = request.getParameter("left");
        int left = paramLeft == null || paramLeft.isBlank() ? 0 : Integer.parseInt(paramLeft);

        String paramRight = request.getParameter("right");
        int right = paramRight == null || paramRight.isBlank() ? 0 : Integer.parseInt(paramRight);

        int result = service.add(left, right);
        response.getWriter().println("Result is: " + result);
    }
}
