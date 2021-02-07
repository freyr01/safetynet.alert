package com.safetynet.alert.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alert.dao.IFireStationDAO;
import com.safetynet.alert.dao.IPersonDAO;
import com.safetynet.alert.dto.AddressReportDTO;
import com.safetynet.alert.dto.AddressReportPersonDTO;
import com.safetynet.alert.dto.ChildInfoDTO;
import com.safetynet.alert.dto.PersonInfoDTO;
import com.safetynet.alert.exception.MedicalRecordNotFoundException;
import com.safetynet.alert.model.FireStationMapping;
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
	
	public List<PersonInfoDTO> getPersonInfo(String firstName, String lastName) {
		List<PersonInfoDTO> personInfoList = new ArrayList<PersonInfoDTO>();
		List<Person> persons;
		if(firstName == null) {
			persons = personDao.findByLastName(lastName);
		} else {
			persons = personDao.findByFullName(firstName, lastName);
		}
		for(Person p : persons) {
			PersonInfoDTO personInfo = new PersonInfoDTO();
			MedicalRecord medicalRecord = null;
			int age;
			List<String> allergies;
			List<String> medications;
			try {
				medicalRecord = medicalRecordService.getMedicalRecordOf(p.getFirstName(), p.getLastName());
				age = medicalRecordService.getAgeOf(medicalRecord);
				allergies = medicalRecord.getAllergies();
				medications = medicalRecord.getMedications();
			} catch (MedicalRecordNotFoundException e) {
				log.error("No medical record found for person: {} {}, age is set to -1, allergies and medication set to empty", p.getFirstName(), p.getLastName());
				age = -1;
				allergies = new ArrayList<String>();
				medications = new ArrayList<String>();
			}
			
			personInfo.setFirstName(p.getFirstName());
			personInfo.setLastName(p.getLastName());
			personInfo.setAge(age);
			personInfo.setAddress(p.getAddress());
			personInfo.setEmail(p.getEmail());
			personInfo.setAllergies(allergies);
			personInfo.setMedications(medications);
			
			personInfoList.add(personInfo);
		}
		
		return personInfoList;
	}
	
	@Override
	public List<PersonInfoDTO> getPersonInfo(String lastName) {
		// TODO Auto-generated method stub
		return null;
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
	
	public List<ChildInfoDTO> getChildByAddress(String address) {
		if(address == null) {
			log.error("null arg not allowed");
			return null;
		}
		
		List<Person> personsByAddress = personDao.findByAddress(address);
		List<ChildInfoDTO> childsInfo = new ArrayList<ChildInfoDTO>();

		for(Person p : personsByAddress) {
			MedicalRecord medicalRecord = null;
			try {
				medicalRecord = medicalRecordService.getMedicalRecordOf(p.getFirstName(), p.getLastName());
			} catch (MedicalRecordNotFoundException e) {
				log.error("No medical record found for person: {} {}, skip this person", p.getFirstName(), p.getLastName());
				continue;
			}
			int personAge;
	
			personAge = medicalRecordService.getAgeOf(medicalRecord);

			if(medicalRecordService.isChild(medicalRecord)) {
				List<Person> famillyMember = new ArrayList<Person>(personsByAddress);
				famillyMember.remove(p);
				
				ChildInfoDTO childInfo = new ChildInfoDTO(p.getFirstName(), p.getLastName(), personAge, famillyMember);
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
	public AddressReportDTO getAddressReport(String address) {
		AddressReportDTO addressReportDTO = new AddressReportDTO();

		FireStationMapping fireStationMapping = fireStationDAO.findByAddress(address);
		if(fireStationMapping == null) {
			return null;
		}
		
		addressReportDTO.setStationNumber(fireStationMapping.getStation());
		addressReportDTO.setPerson(getAddressReportPersonDTO(address));
		
		return addressReportDTO;
	}
	
	public List<AddressReportPersonDTO> getAddressReportPersonDTO(String address){
		List<AddressReportPersonDTO> listAddressReportPersonDTO = new ArrayList<AddressReportPersonDTO>();
		List<Person> personsByAddress = personDao.findByAddress(address);
		for(Person person : personsByAddress) {
			AddressReportPersonDTO addressReportPersonDTO = new AddressReportPersonDTO();
			MedicalRecord medicalRecord;
			int age;
			List<String> allergies;
			List<String> medications;
			
			try {
				medicalRecord = medicalRecordService.getMedicalRecordOf(person.getFirstName(), person.getLastName());
				age = medicalRecordService.getAgeOf(medicalRecord);
				allergies = medicalRecord.getAllergies();
				medications = medicalRecord.getMedications();
			} catch (MedicalRecordNotFoundException e) {
				log.error("No medical record found for person: {} {}, age is set to -1, allergies and medication set to empty", person.getFirstName(), person.getLastName());
				age = -1;
				allergies = new ArrayList<String>();
				medications = new ArrayList<String>();
			}
			
			addressReportPersonDTO.setFirstName(person.getFirstName());
			addressReportPersonDTO.setLastName(person.getLastName());
			addressReportPersonDTO.setAge(age);
			addressReportPersonDTO.setPhone(person.getPhone());
			addressReportPersonDTO.setAllergies(allergies);
			addressReportPersonDTO.setMedications(medications);
			
			listAddressReportPersonDTO.add(addressReportPersonDTO);
		}
		
		
		return listAddressReportPersonDTO;
	}


	
}
