package com.wbc.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {
	
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	
	
	public void sendMessagetoTopic(String kafkaTopic,String message) {
		
		kafkaTemplate.send(kafkaTopic, message);
		
	}
}
