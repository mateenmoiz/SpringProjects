package com.boot.controller;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.service.JMSMessageConsumer;
import com.boot.service.JMSMessagePopulator;

@RestController
public class MessageController {

	@Autowired
	private JMSMessagePopulator jmsMessagePopulator;
	
	@Autowired
	private JMSMessageConsumer jmsMessageConsumer;
	
	/*
	 * Web Service to send Messages
	 */

	@RequestMapping("/sendMessage")
	public String sendMessage() {

		try {
			jmsMessagePopulator.sendMessages();
		} catch (JMSException e) {
			e.printStackTrace();
		}

		return "Message Successfully Sent";
	}
	
	
	/*
	 * Web Service to Consume Messages
	 */

	@RequestMapping("/retrieveMessage")
	public String retrieveMessage() {

		try {
			jmsMessageConsumer.consumerMessages();
		} catch (JMSException e) {
			e.printStackTrace();
		}

		return "Message Successfully Retrieved";
	}

}
