package com.safetynet.alert.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alert.dao.PersonDAO;
import com.safetynet.alert.model.Person;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

	@Autowired
	private PersonDAO personDao;
	
	public List<Person> getPersonByFullName(String firstName, String lastname) {
		List<Person> persons = personDao.findAll();
		List<Person> filteredPersons = new ArrayList<Person>();
		
		for(Person p : persons) {
			if(p.getFirstName().equals(firstName) && p.getLastName().equals(lastname)) {
				filteredPersons.add(p);
			}
		}
		
		return filteredPersons;
	}
	
}
