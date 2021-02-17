package com.safetynet.alert.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safetynet.alert.model.MedicalRecord;

@Repository
public class MedicalRecordDAOImpl implements IMedicalRecordDAO {
	static Logger log = LoggerFactory.getLogger(MedicalRecordDAOImpl.class);
	private JsonDatabaseDAOImpl db;
	
	public MedicalRecordDAOImpl(@Autowired JsonDatabaseDAOImpl p_db) {
		db = p_db;
	}
	
	@Override
	public List<MedicalRecord> findAll() {
		return db.getConnection().getMedicalRecords();
	}

	@Override
	public MedicalRecord findByFullName(String firstName, String lastName) {
		for(MedicalRecord mr : db.getConnection().getMedicalRecords()) {
			if(mr.getFirstName().equals(firstName) && mr.getLastName().equals(lastName)) {
				return mr;
			}
		}
		return null;
	}

	@Override
	public MedicalRecord post(MedicalRecord medicalRecord) {

		if(findAll().add(medicalRecord)) {
			return medicalRecord;
		}
		return null;
	}

	@Override
	public MedicalRecord put(MedicalRecord medicalRecord) {
		for(MedicalRecord mr : findAll()) {
			if(mr.getFirstName().equals(medicalRecord.getFirstName()) && mr.getLastName().equals(medicalRecord.getLastName())) {
				if(medicalRecord.getBirthdate() != null && ! medicalRecord.getBirthdate().isEmpty()) {
					mr.setBirthdate(medicalRecord.getBirthdate());
				}
				if(medicalRecord.getAllergies() != null) {
					mr.setAllergies(medicalRecord.getAllergies());
				}
				if(medicalRecord.getMedications() != null) {
					mr.setMedications(medicalRecord.getMedications());
				}
				
				return mr;
			}
		}
		return null;
	}

	@Override
	public MedicalRecord delete(String firstName, String lastName) {
		for(MedicalRecord mr : findAll()) {
			if(mr.getFirstName().equals(firstName) && mr.getLastName().equals(lastName)) {
				findAll().remove(mr);
				return mr;
			}
		}
		return null;
	}

}
