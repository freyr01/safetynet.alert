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
		List<Person> persons = personDao.findByFullName(firstName, lastName);
		
		for(Person p : persons) {
			PersonInfoDTO personInfo = new PersonInfoDTO();
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
	
	public List<ChildInfoDTO> getChildByAddress(String address) {
		if(address == null) {
			log.error("null arg not allowed");
			return null;
		}
		
		List<Person> personsByAddress = personDao.findByAddress(address);
		List<ChildInfoDTO> childsInfo = new ArrayList<ChildInfoDTO>();

		for(Person p : personsByAddress) {
			int personAge = medicalRecordService.getAgeOf(p.getFirstName(), p.getLastName());
			if(personAge > -1 && personAge <= 18) {
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
		List<AddressReportPersonDTO> listAddressReportPersonDTO = new ArrayList<AddressReportPersonDTO>();
		

		int stationNumber = fireStationDAO.findByAddress(address).getStation();
		addressReportDTO.setStationNumber(stationNumber);
		
		List<Person> personsByAddress = personDao.findByAddress(address);
		for(Person person : personsByAddress) {
			AddressReportPersonDTO addressReportPersonDTO = new AddressReportPersonDTO();
			MedicalRecord medicalRecord = medicalRecordService.getMedicalRecordOf(person.getFirstName(), person.getLastName());
			
			addressReportPersonDTO.setFirstName(person.getFirstName());
			addressReportPersonDTO.setLastName(person.getLastName());
			addressReportPersonDTO.setAge(medicalRecordService.getAgeOf(medicalRecord));
			addressReportPersonDTO.setPhone(person.getPhone());
			addressReportPersonDTO.setAllergies(medicalRecord.getAllergies());
			addressReportPersonDTO.setMedications(medicalRecord.getMedications());
			
			listAddressReportPersonDTO.add(addressReportPersonDTO);
		}
		
		addressReportDTO.setPerson(listAddressReportPersonDTO);
		
		return addressReportDTO;
	}
	
}
