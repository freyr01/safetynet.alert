package com.safetynet.alert.service;

import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alert.dao.IPersonDAO;
import com.safetynet.alert.dao.PersonDAOImpl;
import com.safetynet.alert.model.Person;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements IPersonService{
	
	Logger log = LoggerFactory.getLogger(PersonServiceImpl.class);

	@Autowired
	private IPersonDAO personDao;
	
	@Autowired
	private IMedicalRecordService medicalRecordService;
	
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
	
	public List<Object> getChildByAddress(String address) {
		if(address == null) {
			log.error("null arg not allowed");
			return null;
		}
		List<Person> persons = personDao.findAll();
		List<Person> personsAtAddress = new ArrayList<Person>();
		List<Object> childs = new ArrayList<Object>();

		for(Person p : persons) {
			if(p.getAddress().equals(address)) {
				personsAtAddress.add(p);
			}
		}
		
		for(Person p : personsAtAddress) {
			int personAge = medicalRecordService.getAgeOf(p.getFirstName(), p.getLastName());
			if(personAge <= 18) {
				List<Person> notherMember = new ArrayList<Person>(personsAtAddress);
				notherMember.remove(p);

				Object child = new Object() {
					String firstName = p.getFirstName();
					String lastName = p.getLastName();
					int age = personAge;
					List<Person> otherMember = notherMember;
					public String getFirstName() {
						return firstName;
					}
					public String getLastName() {
						return lastName;
					}
					public int getAge() {
						return age;
					}
					public List<Person> getOtherMember() {
						return otherMember;
					}
				};
				childs.add(child);
			}
		}
		
		return childs;
	}
	
}
