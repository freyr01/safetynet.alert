package com.safetynet.alert.service;

import java.util.ArrayList;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alert.dao.IFireStationDAO;
import com.safetynet.alert.dao.IPersonDAO;
import com.safetynet.alert.dao.MedicalRecordDAOImpl;
import com.safetynet.alert.dto.AddressReportDTO;
import com.safetynet.alert.dto.AddressReportPersonDTO;
import com.safetynet.alert.dto.FireStationCoverageDTO;
import com.safetynet.alert.dto.FireStationCoveragePersonDTO;
import com.safetynet.alert.dto.FloodStationCoverageDTO;
import com.safetynet.alert.exception.MedicalRecordNotFoundException;
import com.safetynet.alert.model.FireStationMapping;
import com.safetynet.alert.model.MedicalRecord;
import com.safetynet.alert.model.Person;

import java.util.List;

@Service
public class FireStationServiceImpl implements IFireStationService {
	Logger log = LoggerFactory.getLogger(PersonServiceImpl.class);

	
	private IFireStationDAO fireStationDAO;
	private IPersonDAO personDAO;
	private IMedicalRecordService medicalRecordService;
	private IPersonService personService;
	
	public FireStationServiceImpl(@Autowired IFireStationDAO fireStationDAO,
			@Autowired IPersonDAO p_personDAO,
			@Autowired IMedicalRecordService p_medicalRecordService,
			@Autowired IPersonService p_personService) {
		this.fireStationDAO = fireStationDAO;
		this.personDAO = p_personDAO;
		this.medicalRecordService = p_medicalRecordService;
		this.personService = p_personService;
	}
	
	@Override
	public FireStationCoverageDTO getFireStationCoverageFor(int stationNumber) {
		FireStationCoverageDTO fireStationCoverage = new FireStationCoverageDTO();
		List<FireStationCoveragePersonDTO> fireStationCoveredPersonDTO = new ArrayList<FireStationCoveragePersonDTO>();
		List<Person> personsCovered = this.getCoveredPersonOf(stationNumber);
		int adultCount = 0;
		int childCount = 0;
		
		for(Person person : personsCovered) {
			FireStationCoveragePersonDTO personDTO = new FireStationCoveragePersonDTO();
			personDTO.setFirstName(person.getFirstName());
			personDTO.setLastName(person.getLastName());
			personDTO.setAddress(person.getAddress());
			personDTO.setPhone(person.getPhone());
			fireStationCoveredPersonDTO.add(personDTO);
			MedicalRecord medicalRecord;
			try {
				medicalRecord = medicalRecordService.getMedicalRecordOf(person.getFirstName(), person.getLastName());
				if(medicalRecordService.isChild(medicalRecord)) {
					childCount++;
				} else {
					adultCount++;
				}
			} catch (MedicalRecordNotFoundException e) {
				log.error("No medical record found for person: {} {}, it will be count as an adult", person.getFirstName(), person.getLastName());
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
		List<FireStationMapping> fireStationMappingList = fireStationDAO.findByStationsNumber(Arrays.asList(stationNumber));
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

	@Override
	public FloodStationCoverageDTO getFloodStationCoverageFor(List<Integer> stationsNumber) {
		//TODO output format is not good, need logic replacement here
		List<FireStationMapping> fireStationMappingList = fireStationDAO.findByStationsNumber(stationsNumber);
		List<AddressReportDTO> addressReportList = new ArrayList<AddressReportDTO>();
		FloodStationCoverageDTO floodStationCoverageDTO = new FloodStationCoverageDTO();
		
		for(FireStationMapping fireStationMap : fireStationMappingList) {
			AddressReportDTO addressReportDTO = new AddressReportDTO();
			int fireStationNumber = fireStationMap.getStation();
			String address = fireStationMap.getAddress();
			addressReportDTO.setStationNumber(fireStationNumber);
			addressReportDTO.setPerson(personService.getAddressReportPersonDTO(address));
			addressReportList.add(addressReportDTO);
		}
		
		floodStationCoverageDTO.setAddressReportListDTO(addressReportList);
		
		return floodStationCoverageDTO;
	}

}
