/**
 * 
 */
package com.jkaouech.main;

import javax.jms.JMSException;

import com.jkaouech.jms.producer.Producer;

/**
 * @author Jihed KAOUECH
 *
 */
public class Application {

	private final static String BROKER_URL = "tcp://localhost:61616";
	private final static String QUEUE = "request_queue";
	
	public static void main(String[] args) throws JMSException {
		sendMsg();
	}
	
	private static void sendMsg() {
		Producer producter = new Producer(BROKER_URL, QUEUE);
		producter.sendMessage("hello world");
	}

}
