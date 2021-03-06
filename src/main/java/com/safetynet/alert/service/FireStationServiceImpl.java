package com.safetynet.alert.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alert.dao.IFireStationDAO;
import com.safetynet.alert.dto.FirestationDTO;
import com.safetynet.alert.dto.FloodStationDTO;
import com.safetynet.alert.dto.PersonInfoDTO;
import com.safetynet.alert.exception.MedicalRecordNotFoundException;
import com.safetynet.alert.model.FireStationMapping;
import com.safetynet.alert.model.MedicalRecord;
import com.safetynet.alert.model.Person;

@Service
public class FireStationServiceImpl implements IFireStationService {
	Logger log = LoggerFactory.getLogger(PersonServiceImpl.class);

	
	private IFireStationDAO fireStationDAO;
	private IMedicalRecordService medicalRecordService;
	private IPersonService personService;
	
	public FireStationServiceImpl(@Autowired IFireStationDAO fireStationDAO,
			@Autowired IMedicalRecordService p_medicalRecordService,
			@Autowired IPersonService p_personService) {
		this.fireStationDAO = fireStationDAO;
		this.medicalRecordService = p_medicalRecordService;
		this.personService = p_personService;
	}
	
	@Override
	public FirestationDTO getFireStationCoverageFor(int stationNumber) {
		FirestationDTO fireStationCoverage = new FirestationDTO();
		List<PersonInfoDTO> personInfoDTOList = new ArrayList<PersonInfoDTO>();
		List<Person> personsCovered = this.getCoveredPersonOf(stationNumber);
		int adultCount = 0;
		int childCount = 0;
		
		for(Person person : personsCovered) {
			
			PersonInfoDTO personInfoDTO = new PersonInfoDTO();
			personInfoDTO.setFirstName(person.getFirstName());
			personInfoDTO.setLastName(person.getLastName());
			personInfoDTO.setAddress(person.getAddress());
			personInfoDTO.setPhone(person.getPhone());
			personInfoDTOList.add(personInfoDTO);
			
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
		
		fireStationCoverage.setPersons(personInfoDTOList);
		fireStationCoverage.setChildCount(childCount);
		fireStationCoverage.setAdultCount(adultCount);
		
		return fireStationCoverage;
	}

	@Override
	public List<Person> getCoveredPersonOf(int stationNumber) {
		List<Person> personsCovered = new ArrayList<Person>();
		List<FireStationMapping> fireStationMappingList = fireStationDAO.findByStationsNumber(Arrays.asList(stationNumber));
		
		for(Person person : personService.getAllPerson()) {
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
	public List<FloodStationDTO> getFloodStationCoverageFor(List<Integer> stationsNumber) {
		List<FireStationMapping> fireStationMappingList = fireStationDAO.findByStationsNumber(stationsNumber); //Get the mapping of stations given by stationsNumber 
		List<FloodStationDTO> listFloodStationDTO = new ArrayList<FloodStationDTO>();
		
		for(int station : stationsNumber) {
			FloodStationDTO floodStationDTO = new FloodStationDTO();
			floodStationDTO.setStation(station);
			listFloodStationDTO.add(floodStationDTO);
		}
		
		for(FireStationMapping stationMapping : fireStationMappingList) {
			FloodStationDTO floodStationDTO = getFloodStationDTOByStationId(listFloodStationDTO, stationMapping.getStation());
			Map<String, List<PersonInfoDTO>> personMap = floodStationDTO.getAddresses();
			personMap.put(stationMapping.getAddress(), personService.getPersonInfoListByAddress(stationMapping.getAddress()));
		}

		return listFloodStationDTO;
	}
	
	private FloodStationDTO getFloodStationDTOByStationId(List<FloodStationDTO> list, int stationId) {
		for(FloodStationDTO floodStationDTO : list) {
			if(floodStationDTO.getStation() == stationId) {
				return floodStationDTO;
			}
		}
		
		return null;
	}

	@Override
	public FireStationMapping post(FireStationMapping fireStation) {
		for(FireStationMapping fsm : fireStationDAO.findAll()) {
			if(fsm.getAddress().equals(fireStation.getAddress()) && fsm.getStation() == fireStation.getStation()) {
				log.error("A mapping is already present in database with this address: {}", fsm);
				return null;
			}
		}
		return fireStationDAO.post(fireStation);
	}

	@Override
	public FireStationMapping put(FireStationMapping firestation) {
		return fireStationDAO.update(firestation);
	}

	@Override
	public FireStationMapping delete(String address, int stationId) {
		return fireStationDAO.delete(address, stationId);
	}

	@Override
	public List<FireStationMapping> getByAddress(String address) {
		return fireStationDAO.findByAddress(address);
	}

}
