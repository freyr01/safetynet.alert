package com.safetynet.alert;

import java.io.File;
import java.io.IOException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alert.model.PersonModel;

@SpringBootApplication
public class AlertApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(AlertApplication.class, args);
	}
	
	@Override
	public void run(String[] args) throws IOException{
		ObjectMapper objectMapper = new ObjectMapper();
		PersonModel person = objectMapper.readValue(new File("static/data.json"), PersonModel.class);
		System.out.println(person);
	}

}
