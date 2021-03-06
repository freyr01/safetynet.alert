package com.safetynet.alert.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alert.dto.FirestationDTO;
import com.safetynet.alert.dto.FloodStationDTO;
import com.safetynet.alert.model.FireStationMapping;
import com.safetynet.alert.service.IFireStationService;
import com.safetynet.alert.utils.FilterUtils;
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
		
		FirestationDTO fireStationCoverage = fireStationService.getFireStationCoverageFor(stationNumber);

		log.info("Return object: {}", fireStationCoverage);
		
		return FilterUtils.applyPersoninfoExcludeFilter(fireStationCoverage, "age", "email", "medications", "allergies");
	}
	
	@GetMapping(value="/phoneAlert")
	public List<String> getPhoneOfCoveredPersonBy(@RequestParam int firestation) {
		log.info("GET request /phoneAlert with param: firestation: {}", firestation);
		List<String> phoneList = fireStationService.getPhoneOfAllPersonCoveredBy(firestation);
		log.info("Return List<String>: {}", phoneList);
		return phoneList;
	}
	
	@GetMapping(value="/flood/stations")
	public MappingJacksonValue getFloodStationCoverage(@RequestParam List<Integer> stations) {
		log.info("GET request /flood/stations with param: stationsNumber: {}", stations);
		List<FloodStationDTO> addressReportDTOList = fireStationService.getFloodStationCoverageFor(stations);
		log.info("Return List<AddressReportDTO>: {}", addressReportDTOList);
		return FilterUtils.applyPersoninfoExcludeFilter(addressReportDTOList, "address", "email");
	}
	
	@PostMapping(value="/firestation")
	public ResponseEntity<FireStationMapping> post(@RequestBody FireStationMapping firestation) {
		log.info("POST request /firestation with param: {}", firestation);
		FireStationMapping posted = fireStationService.post(firestation);
		
		if(posted == null) {
			log.error("Error while post new firestation mapping: {}", firestation);
			return ResponseEntity.noContent().build();
		}
		
		log.info("Return status ok with object posted in body: {}", posted);
		
		return ResponseEntity.ok(posted);
	}
	
	@PutMapping(value="/firestation")
	public ResponseEntity<FireStationMapping> put(@RequestBody FireStationMapping firestation) {
		log.info("PUT request /firestation with param: {}", firestation);
		FireStationMapping putted = fireStationService.put(firestation);
		
		if(putted == null) {
			log.error("Error while updating firestation mapping: {}", firestation);
			return ResponseEntity.notFound().build();
		}
		
		log.info("Return status ok with object updated in body: {}", putted);
		
		return ResponseEntity.ok(putted);
	}
	
	@DeleteMapping(value="/firestation")
	public ResponseEntity<FireStationMapping> delete(@RequestParam String address, @RequestParam int stationId) {
		log.info("DELETE request /firestation with param: {} id:", address, stationId);
		FireStationMapping deleted = fireStationService.delete(address, stationId);
		
		if(deleted == null) {
			log.error("Error while deleting firestation mapping: {} - {}", address, stationId);
			return ResponseEntity.notFound().build();
		}
		
		log.info("Return status ok with object deleted in body: {}", deleted);
		
		return ResponseEntity.ok(deleted);
	}
	

}
