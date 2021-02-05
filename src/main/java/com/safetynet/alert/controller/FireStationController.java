package com.safetynet.alert.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alert.dto.FireStationCoverageDTO;
import com.safetynet.alert.dto.FloodStationCoverageDTO;
import com.safetynet.alert.service.IFireStationService;
@RestController
public class FireStationController {
	
	private static Logger log = LoggerFactory.getLogger(PersonController.class);
	
	IFireStationService fireStationService;
	
	public FireStationController(@Autowired IFireStationService p_fireStationService) {
		fireStationService = p_fireStationService;
	}
	
	@GetMapping(value="/firestation")
	public FireStationCoverageDTO getCoveredPersonOf(@RequestParam int stationNumber) {
		log.info("GET request /firestation with param: stationNumber: {}", stationNumber);
		
		FireStationCoverageDTO fireStationCoverage = fireStationService.getFireStationCoverageFor(stationNumber);

		log.info("Return object: {}", fireStationCoverage);
		
		return fireStationCoverage;
	}
	
	@GetMapping(value="/phoneAlert")
	public List<String> getPhoneOfCoveredPersonBy(@RequestParam int firestation) {
		log.info("GET request /phoneAlert with param: firestation: {}", firestation);
		List<String> phoneList = fireStationService.getPhoneOfAllPersonCoveredBy(firestation);
		log.info("Return List<String>: {}", phoneList);
		return phoneList;
	}
	
	@GetMapping(value="/flood/stations")
	public FloodStationCoverageDTO getFloodStationCoverage(@RequestParam List<Integer> stations) {
		log.info("GET request /flood/stations with param: stationsNumber: {}", stations);
		FloodStationCoverageDTO floodStationCoverageDTO = fireStationService.getFloodStationCoverageFor(stations);
		log.info("Return FloodStationCoverageDTO: {}", floodStationCoverageDTO);
		return floodStationCoverageDTO;
	}

}
