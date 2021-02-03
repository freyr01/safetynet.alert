package com.safetynet.alert.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alert.dao.IFireStationDAO;
import com.safetynet.alert.dao.IPersonDAO;
import com.safetynet.alert.dto.FireStationCoverageDTO;
import com.safetynet.alert.dto.FireStationCoveragePersonDTO;
import com.safetynet.alert.model.FireStationMapping;
import com.safetynet.alert.model.Person;

import java.util.List;

@Service
public class FireStationServiceImpl implements IFireStationService {
	Logger log = LoggerFactory.getLogger(PersonServiceImpl.class);

	
	private IFireStationDAO fireStationDAO;
	private IPersonDAO personDAO;
	private IMedicalRecordService medicalRecordService;
	
	public FireStationServiceImpl(@Autowired IFireStationDAO fireStationDAO, @Autowired IPersonDAO p_personDAO, @Autowired IMedicalRecordService p_medicalRecordService) {
		this.fireStationDAO = fireStationDAO;
		this.personDAO = p_personDAO;
		this.medicalRecordService = p_medicalRecordService;
	}
	
	@Override
	public FireStationCoverageDTO getFireStationCoverageFor(int stationNumber) {
		FireStationCoverageDTO fireStationCoverage = new FireStationCoverageDTO();
		List<FireStationCoveragePersonDTO> fireStationCoveredPersonDTO = new ArrayList<FireStationCoveragePersonDTO>();
		List<Person> personsCovered = getCoveredPersonOf(stationNumber);
		int adultCount = 0;
		int childCount = 0;
		
		for(Person person : personsCovered) {
			FireStationCoveragePersonDTO personDTO = new FireStationCoveragePersonDTO();
			personDTO.setFirstName(person.getFirstName());
			personDTO.setLastName(person.getLastName());
			personDTO.setAddress(person.getAddress());
			personDTO.setPhone(person.getPhone());
			fireStationCoveredPersonDTO.add(personDTO);
			if(medicalRecordService.isChild(person.getFirstName(), person.getLastName())) {
				childCount++;
			} else {
				adultCount++;
			}
		}
		
		fireStationCoverage.setPersons(fireStationCoveredPersonDTO);
		fireStationCoverage.setChildCount(childCount);
		fireStationCoverage.setAdultCount(adultCount);
		
		return fireStationCoverage;
	}

	@Override
	public List<Person> getCoveredPersonOf(int stationNumber) {
		List<Person> personsCovered = new ArrayList<Person>();
		List<FireStationMapping> fireStationMappingList = fireStationDAO.findByStationNumber(stationNumber);
		for(Person person : personDAO.findAll()) {
			for(FireStationMapping fsMap : fireStationMappingList) {
				if(person.getAddress().equals(fsMap.getAddress())) {
					personsCovered.add(person);
				}
			}
		}
		
		return personsCovered;
	}

	@Override
	public List<String> getPhoneOfAllPersonCoveredBy(int stationNumber) {
		List<String> phoneList = new ArrayList<String>();
		List<Person> personsCovered = getCoveredPersonOf(stationNumber);
		for(Person person : personsCovered) {
			if( ! phoneList.contains(person.getPhone())) {
				phoneList.add(person.getPhone());
			}
		}
		
		return phoneList;
	}

}
