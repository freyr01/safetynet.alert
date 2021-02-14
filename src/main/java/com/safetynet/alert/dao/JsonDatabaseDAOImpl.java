package com.safetynet.alert.dao;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alert.model.JsonDatabase;

@Repository
public class JsonDatabaseDAOImpl implements IDatabaseDAO{
	
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(JsonDatabaseDAOImpl.class);
	private JsonDatabase db; 
	
	public JsonDatabaseDAOImpl() {
		ObjectMapper objectMapper = new ObjectMapper();
		
		ResourceLoader resourceLoader = new DefaultResourceLoader();
		Resource res = resourceLoader.getResource("classpath:data.json");
		InputStream is = null;
		try {
			is = res.getInputStream();
			db = objectMapper.readValue(is, JsonDatabase.class);
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
	
	public JsonDatabase getConnection() {
		return db;
	}

}
