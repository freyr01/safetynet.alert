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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.alert.dao.IPersonDAO;
import com.safetynet.alert.model.Person;
import com.safetynet.alert.service.IMedicalRecordService;
import com.safetynet.alert.service.IPersonService;
import com.safetynet.alert.service.PersonServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
	
	static List<Person> personByFullName = new ArrayList<Person>();
	static List<Person> personByCity = new ArrayList<Person>();
	static List<Person> persons = new ArrayList<Person>();
	static Person person1 = new Person();
	static Person person2 = new Person();
	static {
		
		person1.setFirstName("Eric");
		person1.setLastName("Jori");
		person1.setCity("Antibes");
		person1.setAddress("5 Ch de Vaugrenier");
		person1.setEmail("eric.jori@gmail.com");
		
		
		person2.setFirstName("Samantha");
		person2.setLastName("Carson");
		person2.setCity("Paris");
		person2.setAddress("123 Gare st Lazare");
		person2.setEmail("samantha.carson@gmail.com");
		
		personByFullName.add(person2);
		personByCity.add(person1);
		persons.add(person1);
		persons.add(person2);

	}
	
	@Mock
	private IPersonDAO personDao;
	
	@Mock
	private IMedicalRecordService medicalRecordService;
	
	private IPersonService personService;
	
	@BeforeEach
	public void setUpPerTest() {
		personService = new PersonServiceImpl(personDao, medicalRecordService);
	}
	
	@Test
	public void getPersonByFullNameTest_shouldReturnWantedPerson() {

		when(personDao.findByFullName(anyString(), anyString())).thenReturn(personByFullName);
		
		List<Person> filteredPersons = personService.getPersonByFullName("Samantha", "Carson");
		
		verify(personDao, Mockito.times(1)).findByFullName(anyString(), anyString());
		assertEquals("Samantha", filteredPersons.get(0).getFirstName());
	}
	
	@Test
	public void getPersonEmailByCityTest_shouldReturnAtLeastOneEmail() {
		when(personDao.findByCity(anyString())).thenReturn(personByCity);
		
		List<String> mails = personService.getPersonEmailByCity("Antibes");
		
		verify(personDao, Mockito.times(1)).findByCity(anyString());
		assertEquals("eric.jori@gmail.com", mails.get(0));
	}
	
	@Test
	public void getChildByAddressTest_shouldReturnAtLeastOneObject() {
		when(medicalRecordService.getAgeOf(anyString(), anyString())).thenReturn(12);
		when(personDao.findByAddress(anyString())).thenReturn(personByCity);
		List<Object> child = personService.getChildByAddress("123 Gare st Lazare");
		
		verify(medicalRecordService, Mockito.times(1)).getAgeOf(anyString(), anyString());
		verify(personDao, Mockito.times(1)).findByAddress(anyString());
		assertNotNull(child.get(0));
	}
	
	@Test
	public void addPersonTest_shouldCallTheMockWithPersonObject() {
		Person p1 = new Person("Geofrey", "Versat", "123 Gare st Lazare", "Paris", "92000", "0101010101", "geofrey.versat@gmail.com");
		personService.add(p1);
		
		verify(personDao, Mockito.times(1)).save(any(Person.class));
	}
	
	@Test
	public void updatePersonTest_shouldCallTheMockWithSomeArgs() {
		personService.update(person1.getLastName(), person1.getFirstName(), person1);
		verify(personDao, Mockito.times(1)).update(anyString(), anyString(), any(Person.class));
	}

}
