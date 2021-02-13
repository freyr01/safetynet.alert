package com.safetynet.alert;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.alert.dao.IFireStationDAO;
import com.safetynet.alert.dao.IPersonDAO;
import com.safetynet.alert.dto.FirestationDTO;
import com.safetynet.alert.dto.FloodStationDTO;
import com.safetynet.alert.exception.MedicalRecordNotFoundException;
import com.safetynet.alert.model.MedicalRecord;
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
		fireStationService = new FireStationServiceImpl(fireStationDAO, medicalRecordService, personService);
	}
	
	@Test
	public void testFireStationDTO_shouldBeCorreclyFilled() throws MedicalRecordNotFoundException {
		
		when(fireStationDAO.findByStationsNumber(Arrays.asList(2))).thenReturn(testData.getFireStationMappingForStation2());
		when(personService.getAllPerson()).thenReturn(testData.getPersons());
		when(medicalRecordService.getMedicalRecordOf(anyString(), anyString())).thenReturn(new MedicalRecord());
		when(medicalRecordService.isChild(any(MedicalRecord.class))).thenReturn(true);
		
		FirestationDTO fireStationCoverage = fireStationService.getFireStationCoverageFor(2);
		
		verify(medicalRecordService, Mockito.times(5)).getMedicalRecordOf(anyString(), anyString());
		verify(medicalRecordService, Mockito.times(5)).isChild(any(MedicalRecord.class));
		verify(fireStationDAO, Mockito.times(1)).findByStationsNumber(Arrays.asList(2));
		verify(personService, Mockito.times(1)).getAllPerson();
		
		assertEquals(0, fireStationCoverage.getAdultCount());
		assertEquals(5, fireStationCoverage.getChildCount());
	}
	
	@Test
	public void testPhoneList_shouldReturnPhoneListOfPersonsCoveredByStation2() {
		when(fireStationDAO.findByStationsNumber(Arrays.asList(2))).thenReturn(testData.getFireStationMappingForStation2());
		when(personService.getAllPerson()).thenReturn(testData.getPersons());
		
		List<String> phoneList = fireStationService.getPhoneOfAllPersonCoveredBy(2);
		
		verify(fireStationDAO, Mockito.times(1)).findByStationsNumber(Arrays.asList(2));
		verify(personService, Mockito.times(1)).getAllPerson();
		
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
