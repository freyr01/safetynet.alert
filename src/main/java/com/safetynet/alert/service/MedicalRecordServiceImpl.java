package com.safetynet.alert.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alert.dao.IMedicalRecordDAO;
import com.safetynet.alert.model.MedicalRecord;

@Service
public class MedicalRecordServiceImpl implements IMedicalRecordService {

	Logger log = LoggerFactory.getLogger(MedicalRecordServiceImpl.class);
	
	IMedicalRecordDAO medicalRecordDAO;
	
	public MedicalRecordServiceImpl(@Autowired IMedicalRecordDAO p_medicalRecordDAO) {
		medicalRecordDAO = p_medicalRecordDAO;
	}
	
	@Override
	public int getAgeOf(String firstName, String lastName) {
		MedicalRecord medicalRecord = getMedicalRecordOf(firstName, lastName);
		if(medicalRecord == null) {
			return -1;
		}
		return getAgeOf(medicalRecord);
	}

	@Override
	public MedicalRecord getMedicalRecordOf(String firstName, String lastName) {
		return medicalRecordDAO.findByFullName(firstName, lastName);
	}
	
	public int getAgeOf(MedicalRecord medicalRecord) {
		LocalDate birthDate = null;
		
		try {
			birthDate = LocalDate.parse(medicalRecord.getBirthdate(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		} catch (DateTimeParseException e) {
			log.error("Cannot parse date: {}", medicalRecord.getBirthdate() );
			e.printStackTrace();
			return -1;
		}
		Period agePeriod = Period.between(birthDate, LocalDate.now());	
 
		return agePeriod.getYears();
	}

	@Override
	public boolean isChild(String firstName, String lastName) {
		int age = getAgeOf(firstName, lastName);
		
		if(age <= 18) return true;
		
		return false;
	}

}
