package com.safetynet.alert.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alert.dao.IFireStationDAO;
import com.safetynet.alert.dao.IPersonDAO;
import com.safetynet.alert.dto.AddressReport;
import com.safetynet.alert.dto.ChildInfo;
import com.safetynet.alert.dto.PersonInfo;
import com.safetynet.alert.model.MedicalRecord;
import com.safetynet.alert.model.Person;

@Service
public class PersonServiceImpl implements IPersonService{
	
	Logger log = LoggerFactory.getLogger(PersonServiceImpl.class);

	
	private IPersonDAO personDao;
	private IMedicalRecordService medicalRecordService;
	private IFireStationDAO fireStationDAO;
	
	public PersonServiceImpl(@Autowired IPersonDAO p_personDao, @Autowired IMedicalRecordService p_medicalRecordService, @Autowired IFireStationDAO p_fireStationDAO) {
		personDao = p_personDao;
		medicalRecordService = p_medicalRecordService;
		fireStationDAO = p_fireStationDAO;
	}
	
	public List<PersonInfo> getPersonInfo(String firstName, String lastName) {
		List<PersonInfo> personInfoList = new ArrayList<PersonInfo>();
		List<Person> persons = personDao.findByFullName(firstName, lastName);
		
		for(Person p : persons) {
			PersonInfo personInfo = new PersonInfo();
			MedicalRecord medicalRecord = medicalRecordService.getMedicalRecordOf(p.getFirstName(), p.getLastName());
			int age = medicalRecordService.getAgeOf(medicalRecord);
			
			personInfo.setFirstName(p.getFirstName());
			personInfo.setLastName(p.getLastName());
			personInfo.setAge(age);
			personInfo.setAddress(p.getAddress());
			personInfo.setEmail(p.getEmail());
			personInfo.setAllergies(medicalRecord.getAllergies());
			personInfo.setMedications(medicalRecord.getMedications());
			
			personInfoList.add(personInfo);
		}
		
		return personInfoList;
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
	public Person update(String firstName, String lastName, Person newPersonDatas) {
		return personDao.update(firstName, lastName, newPersonDatas);
	}

	@Override
	public Person delete(String firstName, String lastName) {
		return personDao.delete(firstName, lastName);
	}

	@Override
	public AddressReport getAddressReport(String address) {
		List<Person> personsByAddress = personDao.findByAddress(address);
		int station = fireStationDAO.findByAddress(address).getStation();
		//TODO
		return null;
	}
	
}
