package com.boot.service;

import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;

import org.springframework.stereotype.Component;

import com.boot.base.AbstractMessageUtil;
import com.boot.base.DelayedMessageListener;

@Component
public class JMSMessageConsumer extends AbstractMessageUtil {

	private List<MessageConsumer> consumers = new ArrayList<>();
	private List<Session> sessions = new ArrayList<>();

	public void consumerMessages() throws JMSException {
		for (int x = 0; x < 5; x++) {
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue queue = session.createQueue("TEST_DESTINATION");
			sessions.add(session);
			MessageConsumer consumer = session.createConsumer(queue);
			if (x % 2 == 0) {
				consumer.setMessageListener(
						new DelayedMessageListener(String.valueOf(x) + " Quick Consumer with less delay", 10));
			} else {
				consumer.setMessageListener(
						new DelayedMessageListener(String.valueOf(x) + " Slow Consumer with more delay", 100));
			}
			consumers.add(consumer);
		}
		connection.start();
	}

	@Override
	public void shutdown() throws JMSException {

		for (MessageConsumer consumer : consumers) {
			consumer.close();
		}
		for (Session session : sessions) {
			session.close();
		}

		super.shutdown();
	}

}
