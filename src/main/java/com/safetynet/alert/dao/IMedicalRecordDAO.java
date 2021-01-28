package com.safetynet.alert.dao;

import java.util.List;

import com.safetynet.alert.model.MedicalRecord;

public interface IMedicalRecordDAO {
	
	/**
	 * List all medical records
	 * @return List<MedicalRecord>
	 * @author Mathias Lauer
	 * 28 janv. 2021
	 */
	public List<MedicalRecord> findAll();
	
	/**
	 * Should return a medical record of a person
	 * @param firstName
	 * @param lastName
	 * @return MedicalRecord or null if not found
	 * @author Mathias Lauer
	 * 28 janv. 2021
	 */
	public MedicalRecord findByFullName(String firstName, String lastName);

}
