package com.safetynet.alert;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.alert.dao.IFireStationDAO;
import com.safetynet.alert.dao.IPersonDAO;
import com.safetynet.alert.dto.FireStationCoverageDTO;
import com.safetynet.alert.model.FireStationMapping;
import com.safetynet.alert.model.Person;
import com.safetynet.alert.service.FireStationServiceImpl;
import com.safetynet.alert.service.IFireStationService;
import com.safetynet.alert.service.IMedicalRecordService;

@ExtendWith(MockitoExtension.class)
public class FireStationServiceTest {
	
	@Mock
	private IPersonDAO personDAO;
	
	@Mock
	private IMedicalRecordService medicalRecordService;
	
	@Mock
	private IFireStationDAO fireStationDAO;
	
	private IFireStationService fireStationService;
	
	@BeforeEach
	public void setUpPerTest() {
		fireStationService = new FireStationServiceImpl(fireStationDAO, personDAO, medicalRecordService);
	}
	
	@Test
	public void testStationCoverage() {
		when(fireStationDAO.findByStationNumber(1)).thenReturn(TestData.getTestFireStationMapping());
		when(personDAO.findAll()).thenReturn(persons);
		when(medicalRecordService.isChild(anyString(), anyString())).thenReturn(false);
		
		FireStationCoverageDTO fireStationCoverage = fireStationService.getFireStationCoverageFor(1);
		
		assertEquals(1, fireStationCoverage.getAdultCount());
		assertEquals(0, fireStationCoverage.getChildCount());
		assertEquals("Eric", fireStationCoverage.getPersons().get(0).getFirstName());
	}
	
	@Test
	public void testPhoneListOfCoveragePerson() {
		when(fireStationDAO.findByStationNumber(1)).thenReturn(fireStationMapping);
		when(personDAO.findAll()).thenReturn(persons);
		
		List<String> phoneList = fireStationService.getPhoneOfAllPersonCoveredBy(1);
		
		assertEquals("0401010101", phoneList.get(0));
	}

}
