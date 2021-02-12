package com.safetynet.alert;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.alert.dao.IFireStationDAO;
import com.safetynet.alert.dao.IPersonDAO;
import com.safetynet.alert.dto.FireDTO;
import com.safetynet.alert.dto.FirestationDTO;
import com.safetynet.alert.dto.FloodStationDTO;
import com.safetynet.alert.exception.MedicalRecordNotFoundException;
import com.safetynet.alert.model.MedicalRecord;
import com.safetynet.alert.model.Person;
import com.safetynet.alert.service.FireStationServiceImpl;
import com.safetynet.alert.service.IFireStationService;
import com.safetynet.alert.service.IMedicalRecordService;
import com.safetynet.alert.service.IPersonService;

@ExtendWith(MockitoExtension.class)
public class FireStationServiceTest {
	
	@Mock
	private IPersonDAO personDAO;
	
	@Mock
	private IMedicalRecordService medicalRecordService;
	
	@Mock
	private IFireStationDAO fireStationDAO;
	
	@Mock
	private IPersonService personService;
	
	private IFireStationService fireStationService;
	
	private static TestData testData = new TestData();
	
	@BeforeEach
	public void setUpPerTest() {
		fireStationService = new FireStationServiceImpl(fireStationDAO, personDAO, medicalRecordService, personService);
		//TODO PersonDAO ne devrait pas etre une dépendance de ce service
	}
	
	@Test
	public void testFireStationDTO_shouldCorreclyFilled() throws MedicalRecordNotFoundException {
		
		when(fireStationDAO.findByStationsNumber(Arrays.asList(2))).thenReturn(testData.getFireStationMappingForStation2());
		when(personDAO.findAll()).thenReturn(testData.getPersons());
		when(medicalRecordService.getMedicalRecordOf(anyString(), anyString())).thenReturn(new MedicalRecord());
		when(medicalRecordService.isChild(any(MedicalRecord.class))).thenReturn(true);
		
		FirestationDTO fireStationCoverage = fireStationService.getFireStationCoverageFor(2);
		
		assertEquals(0, fireStationCoverage.getAdultCount());
		assertEquals(5, fireStationCoverage.getChildCount());
	}
	
	@Test
	public void testPhoneListOfCoveragePerson() {
		when(fireStationDAO.findByStationsNumber(Arrays.asList(2))).thenReturn(testData.getFireStationMappingForStation2());
		when(personDAO.findAll()).thenReturn(testData.getPersons());
		
		List<String> phoneList = fireStationService.getPhoneOfAllPersonCoveredBy(2);
		
		assertEquals(4, phoneList.size());
	}
	
	@Test
	public void testFloodStationDTO_shouldCorreclyFilled() {
		when(fireStationDAO.findByStationsNumber(Arrays.asList(2))).thenReturn(testData.getFireStationMappingForStation2());
		when(personService.getPersonInfoListByAddress("29 15th St")).thenReturn(testData.getPersonForAddress2915thSt());
		when(personService.getPersonInfoListByAddress("892 Downing Ct")).thenReturn(testData.getPersonForAddress892DowningCt());
		when(personService.getPersonInfoListByAddress("951 LoneTree Rd")).thenReturn(testData.getPersonForAddress951LoneTreeRd());
		
		List<FloodStationDTO> floodStationList = fireStationService.getFloodStationCoverageFor(Arrays.asList(2));
		
		assertEquals(1, floodStationList.size());
		assertEquals(2, floodStationList.get(0).getStation());
	}

}
