package com.safetynet.alert.service;

import java.time.Duration;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;

import com.safetynet.alert.dao.IMedicalRecordDAO;
import com.safetynet.alert.model.MedicalRecord;

public class MedicalRecordServiceImpl implements IMedicalRecordService {

	@Autowired
	IMedicalRecordDAO medicalRecordDAO;
	
	@Override
	public int getAgeOf(String firstName, String lastName) {
		for(MedicalRecord mr : medicalRecordDAO.findAll()) {
			if(mr.getFirstName().equals(firstName) && mr.getLastName().equals(lastName)) {
				Instant birthDate = Instant.parse(mr.getBirthdate());
				Duration ageInDuration = Duration.between(birthDate, Instant.now());
				long ageInDays = ageInDuration.toDays();
				return (int)ageInDays / 365;
			}
		}
		return -1;
	}

}
