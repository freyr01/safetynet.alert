package com.safetynet.alert.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alert.dao.IFireStationDAO;
import com.safetynet.alert.dao.IPersonDAO;
import com.safetynet.alert.dto.FireStationCoverage;
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
	public FireStationCoverage getCoveredFolkOf(int stationNumber) {
		FireStationCoverage fireStationJurisdiction = new FireStationCoverage();
		List<Person> personsUnderJurisdiction = new ArrayList<Person>();
		List<FireStationMapping> fireStationMappingList = fireStationDAO.findByStationNumber(stationNumber);
		int adultCount = 0;
		int childCount = 0;
		
		for(Person person : personDAO.findAll()) {
			for(FireStationMapping fsMap : fireStationMappingList) {
				if(person.getAddress().equals(fsMap.getAddress())) {
					personsUnderJurisdiction.add(person);
					if(medicalRecordService.isChild(person.getFirstName(), person.getLastName())) {
						childCount++;
					} else {
						adultCount++;
					}
				}
			}
		}
		
		fireStationJurisdiction.setPersons(personsUnderJurisdiction);
		fireStationJurisdiction.setChildCount(childCount);
		fireStationJurisdiction.setAdultCount(adultCount);

		return fireStationJurisdiction;
	}

}
