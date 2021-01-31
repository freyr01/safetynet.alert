package com.safetynet.alert.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alert.dto.FireStationCoverage;
import com.safetynet.alert.service.IFireStationService;
@RestController
public class FireStationController {
	
	private static Logger log = LoggerFactory.getLogger(PersonController.class);
	
	IFireStationService fireStationService;
	
	public FireStationController(@Autowired IFireStationService p_fireStationService) {
		fireStationService = p_fireStationService;
	}
	
	@GetMapping(value="/firestation")
	public FireStationCoverage getCoveredFolkOf(@RequestParam int stationNumber) {
		log.info("GET request /firestation with param: stationNumber: {}", stationNumber);
		FireStationCoverage fireStationJurisdiction = fireStationService.getCoveredFolkOf(stationNumber);
		log.info("Return object FireStationJurisdicton: {}", fireStationJurisdiction);
		
		return fireStationJurisdiction;
	}

}
