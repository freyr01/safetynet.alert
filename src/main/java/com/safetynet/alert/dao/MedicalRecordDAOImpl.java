package com.safetynet.alert.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safetynet.alert.model.MedicalRecord;

@Repository
public class MedicalRecordDAOImpl implements IMedicalRecordDAO {

	@Autowired
	DatabaseDAO db;
	
	@Override
	public List<MedicalRecord> findAll() {
		return db.getDb().getMedicalRecords();
	}

}
