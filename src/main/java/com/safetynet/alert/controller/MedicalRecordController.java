package com.safetynet.alert.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alert.model.MedicalRecord;
import com.safetynet.alert.service.IMedicalRecordService;

@RestController
public class MedicalRecordController {
	
	private IMedicalRecordService medicalRecordService;
	
	public MedicalRecordController(@Autowired IMedicalRecordService p_medicalRecordService) {
		medicalRecordService = p_medicalRecordService;
	}
	
	private static Logger log = LoggerFactory.getLogger(MedicalRecordController.class);
	
	@PostMapping(value="medicalRecord")
	public ResponseEntity<MedicalRecord> post(@RequestBody MedicalRecord medicalRecord) {
		log.info("POST request /medicalRecord with param: {}", medicalRecord);
		MedicalRecord posted = medicalRecordService.post(medicalRecord);
		
		if(posted == null) {
			log.error("Error while post new medical record: {}", medicalRecord);
			return ResponseEntity.noContent().build();
		}
		
		log.info("Return status ok with object posted in body: {}", posted);
		
		return ResponseEntity.ok(posted);
	}
	
	@PutMapping(value="medicalRecord")
	public ResponseEntity<MedicalRecord> put(@RequestBody MedicalRecord medicalRecord) {
		log.info("PUT request /medicalRecord with param: {}", medicalRecord);
		MedicalRecord putted = medicalRecordService.put(medicalRecord);
		
		if(putted == null) {
			log.error("Error while putting medical record: {}", medicalRecord);
			return ResponseEntity.notFound().build();
		}
		
		log.info("Return status ok with object posted in body: {}", putted);
		
		return ResponseEntity.ok(putted);
	}
	
	@DeleteMapping(value="medicalRecord")
	public ResponseEntity<MedicalRecord> delete(@RequestParam String firstName, @RequestParam String lastName) {
		log.info("DELETE request /medicalRecord with param: {} {}", firstName, lastName);
		MedicalRecord deletted = medicalRecordService.delete(firstName, lastName);
		
		if(deletted == null) {
			log.error("Error while deleting medical record: {} {}", firstName, lastName);
			return ResponseEntity.notFound().build();
		}
		
		log.info("Return status ok with object deleted in body: {}", deletted);
		
		return ResponseEntity.ok(deletted);
	}

}
