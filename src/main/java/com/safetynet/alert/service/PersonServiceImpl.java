package com.safetynet.alert.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alert.dao.IPersonDAO;
import com.safetynet.alert.dto.ChildInfo;
import com.safetynet.alert.model.Person;

@Service
public class PersonServiceImpl implements IPersonService{
	
	Logger log = LoggerFactory.getLogger(PersonServiceImpl.class);

	
	private IPersonDAO personDao;
	private IMedicalRecordService medicalRecordService;
	
	public PersonServiceImpl(@Autowired IPersonDAO p_personDao, @Autowired IMedicalRecordService p_medicalRecordService) {
		personDao = p_personDao;
		medicalRecordService = p_medicalRecordService;
	}
	
	public List<Person> getPersonByFullName(String firstName, String lastName) {
		return personDao.findByFullName(firstName, lastName);
	}
	
	public List<String> getPersonEmailByCity(String city) {
		if(city == null) {
			log.error("null arg not allowed");
			return null;
		}
		ArrayList<String> mails = new ArrayList<String>();
		for(Person p : personDao.findByCity(city)) {
			String mail = p.getEmail();
			if( ! mails.contains(mail)) {	//Check email not already added
				mails.add(p.getEmail());	//Add email
			}
		}
		
		return mails;
	}
	
	public List<ChildInfo> getChildByAddress(String address) {
		if(address == null) {
			log.error("null arg not allowed");
			return null;
		}
		
		List<Person> personsByAddress = personDao.findByAddress(address);
		List<ChildInfo> childsInfo = new ArrayList<ChildInfo>();

		for(Person p : personsByAddress) {
			int personAge = medicalRecordService.getAgeOf(p.getFirstName(), p.getLastName());
			if(personAge > -1 && personAge <= 18) {
				List<Person> famillyMember = new ArrayList<Person>(personsByAddress);
				famillyMember.remove(p);
				
				ChildInfo childInfo = new ChildInfo(p.getFirstName(), p.getLastName(), personAge, famillyMember);
				childsInfo.add(childInfo);
			}
		}
		
		return childsInfo;
	}

	public Person add(Person person) {
		
		personDao.save(person);
		
		return person;
	}

	@Override
	public Person update(String lastName, String firstName, Person newPersonDatas) {
		return personDao.update(lastName, firstName, newPersonDatas);
	}
	
}
