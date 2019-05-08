package com.boot.base;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by Mateen Moiz 
 */
public class DelayedMessageListener implements MessageListener {

    private long delay = 100L;
    private String id = "Listener";

    public DelayedMessageListener(String id, long delay) {
        this.delay = delay;
        this.id = id;
    }

    @Override
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage)message;
            System.out.println(id + " received : " + textMessage.getText());
            Thread.sleep(delay);
        } catch (JMSException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
