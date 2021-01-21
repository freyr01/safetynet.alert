package com.safetynet.alert;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.alert.dao.PersonDAO;
import com.safetynet.alert.model.Person;
import com.safetynet.alert.service.PersonService;

@SpringBootTest
public class PersonServiceTest {
	
	static ArrayList<Person> persons = new ArrayList<Person>();
	static {
		Person p1 = new Person();
		p1.setFirstName("Eric");
		p1.setLastName("Jori");
		Person p2 = new Person();
		p2.setFirstName("Samantha");
		p2.setLastName("Carson");
		
		persons.add(p1);
		persons.add(p2);
	}
	
	@Mock
	PersonDAO personDao;
	
	@InjectMocks
	PersonService personService;
	
	@Test
	public void getPersonByFullNameTest_shouldReturnWantedPerson() {

		when(personDao.findAll()).thenReturn(persons);
		List<Person> filteredPersons = personService.getPersonByFullName("Samantha", "Carson");
		
		verify(personDao, Mockito.times(1)).findAll();
		assertEquals("Samantha", filteredPersons.get(0).getFirstName());
	}

}
