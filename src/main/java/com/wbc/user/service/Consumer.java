package com.wbc.user.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
	@KafkaListener(topics = "update_topic_kafka", groupId = "user")
	public void listenGroupUpdate(String message) {
	    System.out.println("Received Message : " + message);
	}
	
	@KafkaListener(topics = "delete_topic_kafka", groupId = "user")
	public void listenGroupDelte(String message) {
	    System.out.println("Received Message : " + message);
	}
}
