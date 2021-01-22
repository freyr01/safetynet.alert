package com.safetynet.alert.dao;

import java.util.List;

import com.safetynet.alert.model.MedicalRecord;

public interface IMedicalRecordDAO {
	
	public List<MedicalRecord> findAll();

}
