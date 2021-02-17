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
import com.safetynet.alert.exception.MedicalRecordNotFoundException;
import com.safetynet.alert.model.MedicalRecord;

@Service
public class MedicalRecordServiceImpl implements IMedicalRecordService {

	Logger log = LoggerFactory.getLogger(MedicalRecordServiceImpl.class);
	
	IMedicalRecordDAO medicalRecordDAO;
	
	public MedicalRecordServiceImpl(@Autowired IMedicalRecordDAO p_medicalRecordDAO) {
		medicalRecordDAO = p_medicalRecordDAO;
	}
	
	@Override
	public int getAgeOf(String firstName, String lastName) throws MedicalRecordNotFoundException {
		MedicalRecord medicalRecord = getMedicalRecordOf(firstName, lastName);
		return getAgeOf(medicalRecord);
	}

	@Override
	public MedicalRecord getMedicalRecordOf(String firstName, String lastName) throws MedicalRecordNotFoundException {
		MedicalRecord medicalRecord = medicalRecordDAO.findByFullName(firstName, lastName);
		if(medicalRecord == null) {
			throw new MedicalRecordNotFoundException("No medical record found for person: "+ firstName + " " + lastName);
		}
		return medicalRecord;
	}
	
	public int getAgeOf(MedicalRecord medicalRecord) throws DateTimeParseException {
		LocalDate birthDate = LocalDate.parse(medicalRecord.getBirthdate(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));

		Period agePeriod = Period.between(birthDate, LocalDate.now());	
 
		return agePeriod.getYears();
	}

	@Override
	public boolean isChild(MedicalRecord medicalRecord){
		int age = getAgeOf(medicalRecord);
		
		if(age <= 18) return true;
		
		return false;
	}

	@Override
	public MedicalRecord post(MedicalRecord medicalRecord) {
		for(MedicalRecord mr : medicalRecordDAO.findAll()) {
			if(mr.getFirstName().equals(medicalRecord.getFirstName()) && mr.getLastName().equals(medicalRecord.getLastName())) {
				log.error("a medical record already exist for person: {} {}", medicalRecord.getFirstName(), medicalRecord.getLastName());
				return null;
			}
		}
		return medicalRecordDAO.post(medicalRecord);
	}

	@Override
	public MedicalRecord put(MedicalRecord medicalRecord) {
		return medicalRecordDAO.put(medicalRecord);
	}

	@Override
	public MedicalRecord delete(String firstName, String lastName) {
		return medicalRecordDAO.delete(firstName, lastName);
	}

}
