package com.safetynet.alert;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alert.model.Database;

@SpringBootApplication
public class AlertApplication{

	public static void main(String[] args) {
		SpringApplication.run(AlertApplication.class, args);
	}
	
	@Autowired
	private ResourceLoader resourceLoader;

	@Bean
	CommandLineRunner runner() {
		return args -> {
			ObjectMapper objectMapper = new ObjectMapper();
			
			Resource res = resourceLoader.getResource("classpath:static/data.json");
			InputStream is = res.getInputStream();
			Database db = objectMapper.readValue(is, Database.class);
			System.out.println(db.getPersons().get(0));
		};
		
	}

}
