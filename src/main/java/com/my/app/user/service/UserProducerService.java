package com.my.app.user.service;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.ProducerCallback;
import org.springframework.stereotype.Service;

@Service
public class UserProducerService extends Thread {

	private static Log log = LogFactory.getLog(UserProducerService.class);

	@Autowired
	private JmsTemplate jmsTemplate;

	@Override
	public void run() {
		jmsTemplate.execute("testQueue", new ProducerCallback<Object>() {
			@Override
			public Object doInJms(Session session, MessageProducer producer) throws JMSException {
				TextMessage msg = session.createTextMessage("Test message!");
				log.trace("message: " + msg);
				producer.send(msg);
				return null;
			}
		});
	}

}
