package com.jkaouech.jms.consumer;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Consumer {

	private String queue;
	private String brokerUrl;

	private ActiveMQConnectionFactory connectionFactory;

	public Consumer(String brokerUrl, String queue) {
		this.queue = queue;
		this.brokerUrl = brokerUrl;
		// Create a ConnectionFactory
		connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
	}
	
	public void run() {

		try {
			System.out.println(Thread.currentThread().getName() + " | Get Connection from broker << " + brokerUrl + " >>") ;
			// Create a Connection
			Connection connection = connectionFactory.createConnection();
			connection.start();
			String clientID = connection.getClientID();
			System.out.println(Thread.currentThread().getName() + " | Connection Created with ID << " + clientID + " >>") ;

			// Create a Session
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			// Create the destination (Topic or Queue)
			Destination destination = session.createQueue(queue);
			// Create a MessageConsumer from the Session to the Topic or Queue
			MessageConsumer consumer = session.createConsumer(destination);

			while (true) {
				// Wait for a message
				Message message = consumer.receive();
				
				if (message instanceof TextMessage) {
					TextMessage textMessage = (TextMessage) message;
					String text = textMessage.getText();
					System.out.println(Thread.currentThread().getName() + " | Receive JMS | text = " + text );
				} else {
					System.out.println(Thread.currentThread().getName() + " | message: " + message);
				}
			}
		} catch (Exception e) {
			System.out.println("Caught: " + e);
			e.printStackTrace();
		}
	}
}
