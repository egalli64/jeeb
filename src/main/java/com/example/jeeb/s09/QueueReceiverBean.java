/*
 * Introduction to Jakarta Enterprise Edition - Enterprise Bean
 * 
 * https://github.com/egalli64/jeeb
 */
package com.example.jeeb.s09;

import org.jboss.logging.Logger;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;

/**
 * A MD Bean as PTP consumer
 * 
 * Having more than one consumer competing on the same queue, it could not receive a message
 */
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "jms/queue/ExpiryQueue"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue") })
public class QueueReceiverBean implements MessageListener {
    private static final Logger log = Logger.getLogger(QueueReceiverBean.class);

    @Override
    public void onMessage(Message message) {
        TextMessage text = (TextMessage) message;
        try {
            log.info("Message received: " + text.getText());
        } catch (JMSException ex) {
            log.error("Can't consume message " + ex);
        }
    }
}