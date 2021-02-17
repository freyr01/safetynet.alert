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

	/**
	 * Should add a new Medical Record in data base
	 * @param medicalRecord to create
	 * @return MedicalRecord created, null if something goes wrong
	 * @author Mathias Lauer
	 * 8 févr. 2021
	 */
	public MedicalRecord post(MedicalRecord medicalRecord);

	/**
	 * Should update a medical record in data base
	 * @param medicalRecord to update, MedicalRecord.firstName and MedicalRecord.lastName are used to find the MedicalRecord to update, these fields are immutable.
	 * @return MedicalRecord updated, null if something goes wrong
	 * @author Mathias Lauer
	 * 8 févr. 2021
	 */
	public MedicalRecord put(MedicalRecord medicalRecord);

	/**
	 * Should delete a medical record in data base
	 * @param firstName
	 * @param lastName
	 * @return MedicalRecord deleted, null if something goes wrong
	 * @author Mathias Lauer
	 * 8 févr. 2021
	 */
	public MedicalRecord delete(String firstName, String lastName);

}
