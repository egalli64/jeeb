/*
 * Introduction to Jakarta Enterprise Edition - Enterprise Bean
 * 
 * https://github.com/egalli64/jeeb
 */
package com.example.jeeb.s05;

import java.io.IOException;

import org.jboss.logging.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Terminate the session for the current conversation
 */
@SuppressWarnings("serial")
@WebServlet("/s05/down")
public class SessionTerminatorSrv extends HttpServlet {
    private static final Logger log = Logger.getLogger(SessionTerminatorSrv.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.info("");
        request.getSession().invalidate();
    }
}
