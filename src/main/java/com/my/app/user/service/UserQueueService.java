package com.my.app.user.service;

import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

@Service
public class UserQueueService {

	private static final Log log = LogFactory.getLog(UserQueueService.class);

	private QueueConnection connection = null;

	private QueueSession session = null;

	public void run() {
		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		props.put(Context.PROVIDER_URL, "remote://127.0.0.1:4447");
		props.put(Context.SECURITY_PRINCIPAL, "stack");
		props.put(Context.SECURITY_CREDENTIALS, "admin123");

		try {
			InitialContext ctx = new InitialContext(props);
			QueueConnectionFactory factory = (QueueConnectionFactory) ctx.lookup("jms/RemoteConnectionFactory");
			connection = factory.createQueueConnection("stack", "admin123");
			connection.start();

			Queue queue = (Queue) ctx.lookup("jms/queue/test");
			session = connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
			MessageProducer producer = session.createProducer(queue);
			MessageConsumer consumer = session.createConsumer(queue);

			TextMessage sendMessage = session.createTextMessage();
			sendMessage.setText("Hello Queue!");
			producer.send(sendMessage);
			producer.send(sendMessage);
			producer.send(sendMessage);

			consumer.setMessageListener(new UserQueueMessageListener());

//			TextMessage receiveMessage = ((TextMessage) consumer.receive());
//			log.debug("Consumer receive >> " + receiveMessage.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() {
		if (session != null) {
			try {
				session.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (connection != null) {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	class UserQueueMessageListener implements MessageListener {

		@Override
		public void onMessage(Message message) {
			if (message instanceof TextMessage) {
				try {
					TextMessage receiveMessage = (TextMessage) message;
					log.debug("Receive text message >> " + receiveMessage.getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			} else {
				log.debug("Receive message >> " + message);
			}
		}

	}

}
