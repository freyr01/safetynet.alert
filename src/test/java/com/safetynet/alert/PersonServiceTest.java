package com.safetynet.alert;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.alert.dao.PersonDAOImpl;
import com.safetynet.alert.model.Person;
import com.safetynet.alert.service.MedicalRecordServiceImpl;
import com.safetynet.alert.service.PersonServiceImpl;

@SpringBootTest
public class PersonServiceTest {
	
	static ArrayList<Person> persons = new ArrayList<Person>();
	static {
		Person p1 = new Person();
		p1.setFirstName("Eric");
		p1.setLastName("Jori");
		p1.setCity("Antibes");
		p1.setAddress("5 Ch de Vaugrenier");
		p1.setEmail("eric.jori@gmail.com");
		Person p2 = new Person();
		p2.setFirstName("Samantha");
		p2.setLastName("Carson");
		p2.setCity("Paris");
		p2.setAddress("123 Gare st Lazare");
		p2.setEmail("samantha.carson@gmail.com");
		
		persons.add(p1);
		persons.add(p2);
	}
	
	@Mock
	PersonDAOImpl personDao;
	
	@Mock
	MedicalRecordServiceImpl medicalRecordService;
	
	@InjectMocks
	PersonServiceImpl personService;
	
	@Test
	public void getPersonByFullNameTest_shouldReturnWantedPerson() {

		when(personDao.findAll()).thenReturn(persons);
		
		List<Person> filteredPersons = personService.getPersonByFullName("Samantha", "Carson");
		
		verify(personDao, Mockito.times(1)).findAll();
		assertEquals("Samantha", filteredPersons.get(0).getFirstName());
	}
	
	@Test
	public void getPersonEmailByCity_shouldReturnEmail() {
		when(personDao.findAll()).thenReturn(persons);
		
		List<String> mails = personService.getPersonEmailByCity("Antibes");
		
		verify(personDao, Mockito.times(1)).findAll();
		assertEquals("eric.jori@gmail.com", mails.get(0));
	}
	
	@Test
	public void getAgeOfTest() {
		when(medicalRecordService.getAgeOf("Samantha", "Carson")).thenReturn(12);
		when(personDao.findAll()).thenReturn(persons);
		List<Object> child = personService.getChildByAddress("123 Gare st Lazare");
		
		assertNotNull(child.get(0));
	}

}
