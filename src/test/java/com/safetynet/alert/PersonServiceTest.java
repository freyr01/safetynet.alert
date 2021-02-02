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
import com.safetynet.alert.dto.AddressReport;
import com.safetynet.alert.dto.ChildInfo;
import com.safetynet.alert.model.FireStationMapping;
import com.safetynet.alert.model.Person;
import com.safetynet.alert.service.IMedicalRecordService;
import com.safetynet.alert.service.IPersonService;
import com.safetynet.alert.service.PersonServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
	
	static List<Person> personByFullName = new ArrayList<Person>();
	static List<Person> personByCity = new ArrayList<Person>();
	static List<Person> persons = new ArrayList<Person>();
	static List<Person> personsByAddress = new ArrayList<Person>();
	static Person person1 = new Person("Eric", "Jori", "5 Ch de Vaugrenier", "Antibes", "06600" , "0401010101", "eric.jori@gmail.com");
	static Person person2 = new Person("Samantha", "Carson", "123 Gare st Lazare", "Paris", "92000", "0102030405", "samantha.carson@gmail.com");
	static Person person3 = new Person("John", "Watson", "5 Blv de la Mer", "Antibes", "06600", "0403232311", "ejohn.watson@gmail.com");
	static Person person4 = new Person("Geofrey", "Versat", "123 Gare st Lazare", "Paris", "92000", "0101010101", "geofrey.versat@gmail.com");
	static FireStationMapping fireStationMapping = new FireStationMapping();
	static {
		personByFullName.add(person2);
		personByCity.add(person1);
		personByCity.add(person3);
		persons.add(person1);
		persons.add(person2);
		persons.add(person4);
		personsByAddress.add(person2);
		personsByAddress.add(person4);
		fireStationMapping.setAddress("123 Gare st Lazare");
		fireStationMapping.setStation(1);
	}
	
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
	public void testGetPersonInfo_shouldCallDao() {
		personService.getPersonInfo(anyString(), anyString());
		
		verify(personDao, Mockito.times(1)).findByFullName(anyString(), anyString());
	}
	
	@Test
	public void testGetPersonEmailByCity_shouldReturnListOfTwoString() {
		when(personDao.findByCity(anyString())).thenReturn(persons);
		
		List<String> mails = personService.getPersonEmailByCity(anyString());
		
		verify(personDao, Mockito.times(1)).findByCity(anyString());
		assertEquals(3, mails.size());
	}
	
	@Test
	public void testGetChildByAddress_shouldReturnAtLeastOneObject() {
		when(personDao.findByAddress(anyString())).thenReturn(personByCity);
		when(medicalRecordService.getAgeOf(anyString(), anyString())).thenReturn(12);
		
		
		List<ChildInfo> childsInfo = personService.getChildByAddress("123 Gare st Lazare");
		
		verify(medicalRecordService, Mockito.times(2)).getAgeOf(anyString(), anyString());
		verify(personDao, Mockito.times(1)).findByAddress(anyString());
		assertEquals(2, childsInfo.size());
	}
	
	@Test
	public void testAddPerson_shouldCallTheMockWithPersonObject() {
		
		personService.add(new Person());
		
		verify(personDao, Mockito.times(1)).save(any(Person.class));
	}
	
	@Test
	public void testUpdatePerson_shouldCallTheMockWithSomeArgs() {
		personService.update(person1.getLastName(), person1.getFirstName(), person1);
		verify(personDao, Mockito.times(1)).update(anyString(), anyString(), any(Person.class));
	}
	
	@Test
	@Disabled
	public void testGetAddressReport_shouldReturnAddressReportCorrectlyFilled() {
		when(personDao.findByAddress(anyString())).thenReturn(personsByAddress);
		when(fireStationDAO.findByAddress(anyString())).thenReturn(fireStationMapping);
		
		AddressReport addressReport = personService.getAddressReport("123 Gare st Lazare");
		
		assertEquals(1, addressReport.getStationNumber());
		assertEquals(2, addressReport.getPerson().size());
	}
	/*
	List<Person> personsByAddress = personDao.findByAddress(address);
	int station = fireStationDAO.findByAddress(address).getStation(); */

}
