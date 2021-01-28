package com.safetynet.alert.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safetynet.alert.model.MedicalRecord;

@Repository
public class MedicalRecordDAOImpl implements IMedicalRecordDAO {

	private DatabaseDAO db;
	
	public MedicalRecordDAOImpl(@Autowired DatabaseDAO p_db) {
		db = p_db;
	}
	
	@Override
	public List<MedicalRecord> findAll() {
		return db.getDb().getMedicalRecords();
	}

	@Override
	public MedicalRecord findByFullName(String firstName, String lastName) {
		for(MedicalRecord mr : db.getDb().getMedicalRecords()) {
			if(mr.getFirstName().equals(firstName) && mr.getLastName().equals(lastName)) {
				return mr;
			}
		}
		return null;
	}

}
