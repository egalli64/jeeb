/*
 * Introduction to Jakarta Enterprise Edition - Enterprise Bean
 * 
 * https://github.com/egalli64/jeeb
 */
package com.example.jeeb.s10;

import java.io.IOException;

import org.jboss.logging.Logger;

import jakarta.annotation.Resource;
import jakarta.inject.Inject;
import jakarta.jms.Destination;
import jakarta.jms.JMSConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.TextMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Pub-Sub message sender
 */
@SuppressWarnings("serial")
@WebServlet("/s10/message/send")
public class TopicSender extends HttpServlet {
    private static final Logger log = Logger.getLogger(TopicSender.class);

    @Inject
    @JMSConnectionFactory("java:/ConnectionFactory")
    private JMSContext context;

    @Resource(name = "java:/topic/my")
    private Destination destination;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String text = request.getParameter("text");
        if (text == null || text.isBlank()) {
            text = "Empty message";
        }

        log.info("Sending message " + text);

        try {
            TextMessage message = context.createTextMessage(text);
            context.createProducer().send(destination, message);
        } finally {
            response.getWriter().println(String.format("Message %s sent", text));
        }
    }
}