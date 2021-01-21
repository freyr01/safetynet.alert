package com.safetynet.alert.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alert.dao.PersonDAOImpl;
import com.safetynet.alert.model.Person;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements IPersonService{

	@Autowired
	private PersonDAOImpl personDao;
	
	/**
	 * Get persons informations by their full name
	 * @param firstName
	 * @param lastName
	 * @return List<Person>
	 * @author Mathias Lauer
	 * 21 janv. 2021
	 */
	public List<Person> getPersonByFullName(String firstName, String lastname) {
		List<Person> filteredPersons = new ArrayList<Person>();
		
		for(Person p : personDao.findAll()) {
			if(p.getFirstName().equals(firstName) && p.getLastName().equals(lastname)) {
				filteredPersons.add(p);
			}
		}
		
		return filteredPersons;
	}
	
	/**
	 * Get emails for given city
	 * @param city
	 * @return List<String> email list
	 * @author Mathias Lauer
	 * 21 janv. 2021
	 */
	public List<String> getPersonEmailByCity(String city) {
		ArrayList<String> emails = new ArrayList<String>();
		for(Person p : personDao.findAll()) {	//Browse list
			if(p.getCity().equals(city)) {		//Check city
				String email = p.getEmail();
				if( ! emails.contains(email)) {	//Check email not already added
					emails.add(p.getEmail());	//Add email
				}
			}
		}
		
		return emails;
	}
	
}
