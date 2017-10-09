/**
 * 
 */
package com.jkaouech.jms.producer;

import javax.jms.JMSException;

import com.jkaouech.jms.producer.Producer;
import com.jkaouech.jms.utility.ParamConnection;

/**
 * @author Jihed KAOUECH
 *
 */
public class TestProducer {

	public static void main(String[] args) throws JMSException {
		sendMsg();
	}
	
	private static void sendMsg() {
		Producer producter = new Producer(ParamConnection.BROKER_URL, ParamConnection.QUEUE);
		producter.sendMessage("hello world");
	}

}
