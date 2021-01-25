package com.safetynet.alert.dao;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alert.model.JsonDb;
import com.safetynet.alert.model.Person;

@Repository
public class DatabaseDAO {
	
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(DatabaseDAO.class);
	private JsonDb db; 
	
	public DatabaseDAO() {
		ObjectMapper objectMapper = new ObjectMapper();
		
		ResourceLoader resourceLoader = new DefaultResourceLoader();
		Resource res = resourceLoader.getResource("classpath:data.json");
		InputStream is = null;
		try {
			is = res.getInputStream();
			db = objectMapper.readValue(is, JsonDb.class);
		} catch (IOException e) {
			log.error("Error while reading resource", e);
		} finally {
			if(is != null) {
				closeIs(is);
			}
		}
		
	}
	
	private void closeIs(InputStream is) {
		try {
			is.close();
		} catch (IOException e) {
			log.error("Error while closing resource", e);
			e.printStackTrace();
		}
	}
	
	public JsonDb getDb() {
		return db;
	}
	
	//TODO Maybe better way is the use bean to populate each entities
	/*
	@Bean
	  CommandLineRunner initDatabase(Person PersonRepository) {

	    return args -> { 
	    	personRepository.
	    };
	 }
	 */

}
