/*
 * Introduction to Jakarta Enterprise Edition - Enterprise Bean
 * 
 * https://github.com/egalli64/jeeb
 */
package com.example.jeeb.s10;

import org.jboss.logging.Logger;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;

/**
 * A MD Bean as Pub-Sub consumer
 */
@MessageDriven(activationConfig = { //
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "topic/my"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Topic") })
public class TopicReceiverBean implements MessageListener {
    private static final Logger log = Logger.getLogger(TopicReceiverBean.class);

    @Override
    public void onMessage(Message message) {
        TextMessage text = (TextMessage) message;
        try {
            log.info("Message received: " + text.getText());
        } catch (JMSException je) {
            log.error("Can't consume message " + je);
        }
    }
}