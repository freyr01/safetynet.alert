package com.safetynet.alert.integration;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.safetynet.alert.dao.JsonDatabaseDAOImpl;

public class JsonDatabaseIT {
	
	JsonDatabaseDAOImpl databaseDAO = new JsonDatabaseDAOImpl();
	
	@Test
	public void testPersonData_shouldContainAtLeastOneObject() {
		
		assertNotEquals(0, databaseDAO.getConnection().getPersons().size());
	}
	
	@Test
	public void testMedicalRecordData_shouldContainAtLeastOneObject() {
		
		assertNotEquals(0, databaseDAO.getConnection().getMedicalRecords().size());
	}
	
	@Test
	public void testStationMappingData_shouldContainAtLeastOneObject() {
		
		assertNotEquals(0, databaseDAO.getConnection().getFireStations().size());
	}

}
