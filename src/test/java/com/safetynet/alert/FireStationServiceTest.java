package com.safetynet.alert;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
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
import com.safetynet.alert.dto.AddressReportDTO;
import com.safetynet.alert.dto.FireStationCoverageDTO;
import com.safetynet.alert.exception.MedicalRecordNotFoundException;
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
	
	@BeforeEach
	public void setUpPerTest() {
		fireStationService = new FireStationServiceImpl(fireStationDAO, personDAO, medicalRecordService, personService);
	}
	
	@Test
	public void testStationCoverage() throws MedicalRecordNotFoundException {
		List<Person> persons = new ArrayList<Person>();
		Person eric = TestData.TestPerson.ERIC.getPerson();
		Person billy = TestData.TestPerson.BILLY.getPerson();
		persons.add(eric);
		persons.add(billy);
		
		when(fireStationDAO.findByStationsNumber(Arrays.asList(2))).thenReturn(TestData.getTestFireStationMappingList());
		when(personDAO.findAll()).thenReturn(persons);
		when(medicalRecordService.getMedicalRecordOf(eric.getFirstName(), eric.getLastName())).thenReturn(TestData.TestPerson.ERIC.getMedicalRecord());
		when(medicalRecordService.getMedicalRecordOf(billy.getFirstName(), billy.getLastName())).thenReturn(TestData.TestPerson.BILLY.getMedicalRecord());
		
		FireStationCoverageDTO fireStationCoverage = fireStationService.getFireStationCoverageFor(2);
		
		assertEquals(2, fireStationCoverage.getAdultCount());
		assertEquals(0, fireStationCoverage.getChildCount());
		assertEquals("Eric", fireStationCoverage.getPersons().get(0).getFirstName());
	}
	
	@Test
	public void testPhoneListOfCoveragePerson() {
		when(fireStationDAO.findByStationsNumber(Arrays.asList(1))).thenReturn(TestData.getTestFireStationMappingList());
		when(personDAO.findAll()).thenReturn(TestData.TestPerson.getPersonBySameAddress());
		
		List<String> phoneList = fireStationService.getPhoneOfAllPersonCoveredBy(1);
		
		assertEquals("0401010101", phoneList.get(0));
	}
	
	@Test
	public void testFloodStationCoverage_shouldReturnCorreclyFilledListOfAddressReportDTO() {
		when(fireStationDAO.findByStationsNumber(Arrays.asList(1,2))).thenReturn(TestData.getTestFireStationMappingList());
		when(personService.getAddressReportPersonDTO(anyString())).thenReturn(TestData.getAddressReportPersonDTOList());
		
		List<AddressReportDTO> addressReportDTOList = fireStationService.getFloodStationCoverageFor(Arrays.asList(1,2));
		
		assertEquals(1, addressReportDTOList.get(0).getStationNumber());
		
		assertEquals(2, addressReportDTOList.get(1).getStationNumber());
		assertEquals("Eric", addressReportDTOList.get(1).getPerson().get(0).getFirstName());
	}

}
