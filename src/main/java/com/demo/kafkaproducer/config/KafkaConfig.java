package com.demo.kafkaproducer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.demo.kafkaproducer.model.User;

@Configuration
public class KafkaConfig {

	@Bean
	public ProducerFactory producerFactory() {
		
		Map<String,Object> config = new HashMap<>();
		
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");		//Server name(in our case local hence 127.0.0.1)
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);		//Need to config the class we use as key we are using string
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);		//Need to config class we use as value in this case value is JSON object
		
		return new DefaultKafkaProducerFactory(config);
	}
	
	@Bean
	public KafkaTemplate<String,User> kafkaTemplate() {
		
		return new KafkaTemplate<String, User>(producerFactory());
	}
}
