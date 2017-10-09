package com.jkaouech.jms.consumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.jms.JMSException;

import com.jkaouech.jms.utility.ParamConnection;

public class TestConsumer {

	public static void main(String[] args) throws JMSException {
		consmeMsg(1);
	}

	private static void consmeMsg() {
		Consumer consumer = new Consumer(ParamConnection.BROKER_URL, ParamConnection.QUEUE);
		consumer.run();
	}

	private static void consmeMsg(int nbr) {
		ExecutorService executor = Executors.newFixedThreadPool(nbr);
		for (int i = 0; i < nbr; i++) {
			executor.execute(new Runnable() {
				@Override
				public void run() {
					consmeMsg();
				}
			});
		}
	}
	
}
