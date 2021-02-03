package com.safetynet.alert;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.alert.dao.IFireStationDAO;
import com.safetynet.alert.dao.IPersonDAO;
import com.safetynet.alert.dto.AddressReportDTO;
import com.safetynet.alert.dto.ChildInfoDTO;
import com.safetynet.alert.model.FireStationMapping;
import com.safetynet.alert.model.Person;
import com.safetynet.alert.service.IMedicalRecordService;
import com.safetynet.alert.service.IPersonService;
import com.safetynet.alert.service.PersonServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
	

	
	@Mock
	private IPersonDAO personDao;
	
	@Mock
	private IMedicalRecordService medicalRecordService;
	
	@Mock
	private IFireStationDAO fireStationDAO;
	
	private IPersonService personService;
	
	@BeforeEach
	public void setUpPerTest() {
		personService = new PersonServiceImpl(personDao, medicalRecordService, fireStationDAO);
	}
	
	@Test
	public void testGetPersonEmailByCity_shouldReturnListOfThreeMails() {
		when(personDao.findByCity(anyString())).thenReturn(TestData.TestPerson.getBySameCity());
		
		List<String> mails = personService.getPersonEmailByCity(anyString());
		
		verify(personDao, Mockito.times(1)).findByCity(anyString());
		assertEquals(3, mails.size());
	}
	
	@Test
	public void testGetChildByAddress_shouldReturnAtLeastOneObject() {
		when(personDao.findByAddress(anyString())).thenReturn(TestData.TestPerson.getBySameCity());
		when(medicalRecordService.getAgeOf(anyString(), anyString())).thenReturn(12);
		
		
		List<ChildInfoDTO> childsInfo = personService.getChildByAddress("123 Gare st Lazare");
		
		verify(medicalRecordService, Mockito.times(3)).getAgeOf(anyString(), anyString());
		verify(personDao, Mockito.times(1)).findByAddress(anyString());
		assertEquals(3, childsInfo.size());
	}
	
	@Test
	public void testGetAddressReport_shouldReturnAddressReportCorrectlyFilled() {
		List<Person> personsWithSameAddress = new ArrayList<Person>();
		Person eric = TestData.TestPerson.ERIC.getPerson();
		Person billy = TestData.TestPerson.BILLY.getPerson();
		eric.setAddress("123 Gare st Lazare");
		billy.setAddress("123 Gare st Lazare");
		personsWithSameAddress.add(eric);
		personsWithSameAddress.add(billy);
		
		when(personDao.findByAddress(anyString())).thenReturn(personsWithSameAddress);
		when(fireStationDAO.findByAddress(anyString())).thenReturn(TestData.getTestFireStationMapping());
		when(medicalRecordService.getMedicalRecordOf(eric.getFirstName(), eric.getLastName())).thenReturn(TestData.TestPerson.ERIC.getMedicalRecord());
		when(medicalRecordService.getMedicalRecordOf(billy.getFirstName(), billy.getLastName())).thenReturn(TestData.TestPerson.BILLY.getMedicalRecord());
		when(medicalRecordService.getAgeOf(TestData.TestPerson.ERIC.getMedicalRecord())).thenReturn(12);
		when(medicalRecordService.getAgeOf(TestData.TestPerson.BILLY.getMedicalRecord())).thenReturn(21);
		
		AddressReportDTO addressReport = personService.getAddressReport("123 Gare st Lazare");
		
		assertEquals(1, addressReport.getStationNumber());
		assertEquals(2, addressReport.getPerson().size());
	}

}
