package com.safetynet.alert;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.alert.dao.IFireStationDAO;
import com.safetynet.alert.dao.IPersonDAO;
import com.safetynet.alert.dto.ChildInfoDTO;
import com.safetynet.alert.dto.FireDTO;
import com.safetynet.alert.dto.PersonInfoDTO;
import com.safetynet.alert.exception.MedicalRecordNotFoundException;
import com.safetynet.alert.model.MedicalRecord;
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
	private IFireStationDAO fireStationDao;
	
	private IPersonService personService;
	
	private static TestData testData = new TestData();
	
	@BeforeEach
	public void setUpPerTest() {
		personService = new PersonServiceImpl(personDao, medicalRecordService, fireStationDao);
	}
	
	@Test
	public void testGetPersonInfoDTO_shouldReturnCorrectlyFilledPersonInfoDTO() throws MedicalRecordNotFoundException {
		List<Person> persons = new ArrayList<Person>();
		persons.add(testData.getPersons().get(0));
		
		when(personDao.findByFullName("John", "Boyd")).thenReturn(persons);
		when(medicalRecordService.getMedicalRecordOf("John", "Boyd")).thenReturn(testData.getMedicalRecords().get(0));
		when(medicalRecordService.getAgeOf(testData.getMedicalRecords().get(0))).thenReturn(36);
		
		List<PersonInfoDTO> personInfoList = personService.getPersonInfo("John", "Boyd");
		
		verify(personDao, Mockito.times(1)).findByFullName("John", "Boyd");
		verify(medicalRecordService, Mockito.times(1)).getMedicalRecordOf("John", "Boyd");
		verify(medicalRecordService, Mockito.times(1)).getAgeOf(testData.getMedicalRecords().get(0));
		
		assertEquals("John", personInfoList.get(0).getFirstName());
		assertEquals("Boyd", personInfoList.get(0).getLastName());
		assertEquals("1509 Culver St", personInfoList.get(0).getAddress());
		assertEquals("jaboyd@email.com", personInfoList.get(0).getEmail());
		assertEquals(36, personInfoList.get(0).getAge());
		assertEquals("nillacilan", personInfoList.get(0).getAllergies().get(0));
		assertEquals("aznol:350mg", personInfoList.get(0).getMedications().get(0));
		//{ "firstName":"John", "lastName":"Boyd", "address":"1509 Culver St", "city":"Culver", "zip":"97451", "phone":"841-874-6512", "email":"jaboyd@email.com" },
		//{ "firstName":"John", "lastName":"Boyd", "birthdate":"03/06/1984", "medications":["aznol:350mg", "hydrapermazol:100mg"], "allergies":["nillacilan"] },
	}
	
	@Test
	public void testGetListOfMailByCity_shouldReturnListOfAllCulverMails() {
		when(personDao.findByCity(anyString())).thenReturn(testData.getPersons());
		
		List<String> mails = personService.getPersonEmailByCity(anyString());
		
		verify(personDao, Mockito.times(1)).findByCity(anyString());
		
		assertEquals(15, mails.size());
		assertEquals("jaboyd@email.com", mails.get(0));
	}
	
	@Test
	public void testChildInfoDTO_shouldReturnCorreclyFilledDTO() throws MedicalRecordNotFoundException {
		Person child = testData.getPersons().get(3);
		MedicalRecord childMedicalRecord = testData.getMedicalRecords().get(3);
		List<Person> persons = new ArrayList<Person>();
		persons.add(child);
		
		when(personDao.findByAddress(anyString())).thenReturn(persons);
		when(medicalRecordService.getMedicalRecordOf(child.getFirstName(), child.getLastName())).thenReturn(childMedicalRecord);
		when(medicalRecordService.getAgeOf(childMedicalRecord)).thenReturn(12);
		when(medicalRecordService.isChild(any(MedicalRecord.class))).thenReturn(true);
		
		
		List<ChildInfoDTO> childsInfo = personService.getChildByAddress("");
		
		verify(medicalRecordService, Mockito.times(1)).getMedicalRecordOf(child.getFirstName(), child.getLastName());
		verify(medicalRecordService, Mockito.times(1)).isChild(any(MedicalRecord.class));
		verify(medicalRecordService, Mockito.times(1)).getAgeOf(any(MedicalRecord.class));
		verify(personDao, Mockito.times(1)).findByAddress(anyString());
		
		
		assertEquals(1, childsInfo.size());
		assertEquals("Roger", childsInfo.get(0).getFirstName());
		assertEquals("Boyd", childsInfo.get(0).getLastName());
		assertEquals(12, childsInfo.get(0).getAge());
		//persons.add(new Person("Roger", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com")); //3
	}
	
	@Test
	public void testGetFireDto_shouldReturnCorreclyFilledDTO() throws MedicalRecordNotFoundException {
		final String ADDRESS = "1509 Culver St";

		when(fireStationDao.findByAddress(ADDRESS)).thenReturn(testData.getFireStationsCovering1509CulverSt());
		when(personDao.findByAddress(ADDRESS)).thenReturn(testData.getFamillyAt1509CulverSt());
		when(medicalRecordService.getMedicalRecordOf(anyString(), anyString())).thenReturn(new MedicalRecord());
		when(medicalRecordService.getAgeOf(any(MedicalRecord.class))).thenReturn(20);
		
		FireDTO fireDto = personService.getFireDTO(ADDRESS);
		
		verify(fireStationDao, Mockito.times(1)).findByAddress(ADDRESS);
		verify(medicalRecordService, Mockito.times(5)).getAgeOf(any(MedicalRecord.class));
		verify(personDao, Mockito.times(1)).findByAddress(ADDRESS);
		
		assertEquals(3, fireDto.getStationNumber().get(0));
		assertEquals("John", fireDto.getPerson().get(0).getFirstName());
		assertEquals("Boyd", fireDto.getPerson().get(0).getLastName());
		
		/*
        { "firstName":"John", "lastName":"Boyd", "address":"1509 Culver St", "city":"Culver", "zip":"97451", "phone":"841-874-6512", "email":"jaboyd@email.com" },
        { "firstName":"Jacob", "lastName":"Boyd", "address":"1509 Culver St", "city":"Culver", "zip":"97451", "phone":"841-874-6513", "email":"drk@email.com" },
        { "firstName":"Tenley", "lastName":"Boyd", "address":"1509 Culver St", "city":"Culver", "zip":"97451", "phone":"841-874-6512", "email":"tenz@email.com" },
        { "firstName":"Roger", "lastName":"Boyd", "address":"1509 Culver St", "city":"Culver", "zip":"97451", "phone":"841-874-6512", "email":"jaboyd@email.com" },
        { "firstName":"Felicia", "lastName":"Boyd", "address":"1509 Culver St", "city":"Culver", "zip":"97451", "phone":"841-874-6544", "email":"jaboyd@email.com" },
        */
	}
	
	@Test
	public void testGetPersonInfoDTOListAtAddress_shouldReturnCorrectlyFilledDTOList() throws MedicalRecordNotFoundException {
		final String ADDRESS = "1509 Culver St";
		List<Person> personsWithSameAddress = testData.getFamillyAt1509CulverSt();
		
		when(personDao.findByAddress(anyString())).thenReturn(personsWithSameAddress);
		when(medicalRecordService.getMedicalRecordOf(anyString(), anyString())).thenReturn(new MedicalRecord());
		when(medicalRecordService.getAgeOf(any(MedicalRecord.class))).thenReturn(20);
		
		List<PersonInfoDTO> personInfoList = personService.getPersonInfoListByAddress(ADDRESS);
		
		assertEquals(5, personInfoList.size());
		
	}

}
