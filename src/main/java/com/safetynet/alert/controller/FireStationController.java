package com.safetynet.alert.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
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
	public MappingJacksonValue getCoveredPersonOf(@RequestParam int stationNumber) {
		log.info("GET request /firestation with param: stationNumber: {}", stationNumber);
		
		FireStationCoverage fireStationCoverage = fireStationService.getFireStationCoverageFor(stationNumber);
		
		SimpleBeanPropertyFilter fieldFilter = SimpleBeanPropertyFilter.serializeAllExcept("Person.city", "zip", "email");
	    FilterProvider filters = new SimpleFilterProvider().addFilter("stationCoverageFilter", fieldFilter);

	    MappingJacksonValue fireStationCoverageFiltered = new MappingJacksonValue(fireStationCoverage);
	    fireStationCoverageFiltered.setFilters(filters);

		log.info("Return object: {}", fireStationCoverageFiltered);
		
		return fireStationCoverageFiltered;
	}

}
