package com.safetynet.alert.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.safetynet.alert.dao.DatabaseDAO;
import com.safetynet.alert.dao.FireStationDAOImpl;
import com.safetynet.alert.dao.IFireStationDAO;
import com.safetynet.alert.dao.IPersonDAO;
import com.safetynet.alert.dao.PersonDAOImpl;

public class DatabaseIT {
	
	DatabaseDAO databaseDAO = new DatabaseDAO();
	IPersonDAO personDAO =  new PersonDAOImpl(databaseDAO);
	IFireStationDAO fireStationDAO = new FireStationDAOImpl(databaseDAO);
	

	
	@Test
	public void testPersonData_shouldReturnEmailOfFirstPerson() {
		
		assertEquals("jaboyd@email.com", personDAO.findAll().get(0).getEmail());
	}
	
	
	@Test
	public void testStationData_shouldReturnMappingOfFirstStation()
	{
		assertEquals(3, fireStationDAO.findAll().get(0).getStation());
	}

}
