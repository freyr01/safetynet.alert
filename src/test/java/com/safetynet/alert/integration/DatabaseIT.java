package com.safetynet.alert.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.alert.dao.DatabaseDAO;

@SpringBootTest
public class DatabaseIT {
	
	@Autowired
	DatabaseDAO dbDao;
	
	@Test
	public void databaseTest_shouldOpenResource() {
		
		assertEquals("jaboyd@email.com", dbDao.getDb().getPersons().get(0).getEmail());
	}

}
