package com.boot.service;

import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

import com.boot.base.AbstractMessageUtil;

@Component
public class JMSMessagePopulator extends AbstractMessageUtil {

	private List<Session> sessions = new ArrayList<>();

	public JMSMessagePopulator() {
		super();
		System.out.println("In JMSPopulator Constructor");
	}

	public void sendMessages() throws JMSException {

		for (int x = 0; x < 100; x++) {
			messageProducer.send(session.createTextMessage("Message " + x));
		}
	}

	public void sendBulkMessages() throws JMSException {
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue = session.createQueue("TEST_DESTINATION");
		messageProducer = session.createProducer(queue);

		for (int x = 0; x < 100; x++) {
			TextMessage textMessage = session.createTextMessage("Message " + x);
			if (x % 2 == 0) {
				textMessage.setStringProperty("GroupID-JMS", "Even");
			} else {
				textMessage.setStringProperty("GroupID-JMS", "Odd");
			}

			messageProducer.send(textMessage);
		}
	}

	@Override
	public void shutdown() throws JMSException {
		messageProducer.close();

		for (Session session : sessions) {
			session.close();
		}

		super.shutdown();
	}

}
