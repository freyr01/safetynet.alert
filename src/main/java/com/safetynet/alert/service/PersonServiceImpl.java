package com.safetynet.alert.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alert.dao.IPersonDAO;
import com.safetynet.alert.model.Person;

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
	public List<Person> getPersonByFullName(String firstName, String lastName) {
		return personDao.findByFullName(firstName, lastName);
	}
	
	/**
	 * Get emails for given city
	 * @param city
	 * @return List<String> email list
	 * @author Mathias Lauer
	 * 21 janv. 2021
	 */
	public List<String> getPersonEmailByCity(String city) {
		ArrayList<String> mails = new ArrayList<String>();
		for(Person p : personDao.findByCity(city)) {
			String mail = p.getEmail();
			if( ! mails.contains(mail)) {	//Check email not already added
				mails.add(p.getEmail());	//Add email
			}
		}
		
		return mails;
	}
	
	public List<Object> getChildByAddress(String address) {
		if(address == null) {
			log.error("null arg not allowed");
			return null;
		}
		
		List<Person> personsByAddress = personDao.findByAddress(address);
		List<Object> childs = new ArrayList<Object>();

		for(Person p : personsByAddress) {
			int personAge = medicalRecordService.getAgeOf(p.getFirstName(), p.getLastName());
			if(personAge > -1 && personAge <= 18) {
				List<Person> famillyMember = new ArrayList<Person>(personsByAddress);
				famillyMember.remove(p);

				Object child = new Object() {
					public String getFirstName() {
						return p.getFirstName();
					}
					public String getLastName() {
						return p.getLastName();
					}
					public int getAge() {
						return personAge;
					}
					public List<Person> getFamillyMember() {
						return famillyMember;
					}
				};
				childs.add(child);
			}
		}
		
		return childs;
	}

	public Person add(Person person) {
		
		personDao.save(person);
		
		return person;
	}
	
}
