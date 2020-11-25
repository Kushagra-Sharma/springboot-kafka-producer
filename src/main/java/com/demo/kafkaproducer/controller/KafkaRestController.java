package com.demo.kafkaproducer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.kafkaproducer.model.User;

@RestController
@RequestMapping("/kafka")
public class KafkaRestController {

	@Autowired
	KafkaTemplate<String, User> kafkaTemplate;
	private static final String TOPIC = "Kafka_example";
	
	@GetMapping("/publish/{name}")
	public String postMessage(@PathVariable("name") String name) {
		
		kafkaTemplate.send(TOPIC, new User(name,"IT",50000L));
		return "Published Successfully";
	}
}
