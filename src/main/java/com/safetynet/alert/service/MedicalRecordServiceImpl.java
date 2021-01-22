package com.safetynet.alert.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.safetynet.alert.dao.IMedicalRecordDAO;
import com.safetynet.alert.model.MedicalRecord;

public class MedicalRecordServiceImpl implements IMedicalRecordService {

	Logger log = LoggerFactory.getLogger(MedicalRecordServiceImpl.class);
	
	@Autowired
	IMedicalRecordDAO medicalRecordDAO;
	
	@Override
	public int getAgeOf(String firstName, String lastName) {
		for(MedicalRecord mr : medicalRecordDAO.findAll()) {
			if(mr.getFirstName().equals(firstName) && mr.getLastName().equals(lastName)) {
				LocalDate birthDate = null;
				
				try {
					birthDate = LocalDate.parse(mr.getBirthdate(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
				} catch (DateTimeParseException e) {
					log.error("Cannot parse date: {}", mr.getBirthdate() );
					e.printStackTrace();
					return -1;
				}
				Period agePeriod = Period.between(birthDate, LocalDate.now());	
		 
				return agePeriod.getYears();
			}
		}
		log.info("No medical record found for person: {} {}", firstName, lastName);
		return -1;
	}

}
