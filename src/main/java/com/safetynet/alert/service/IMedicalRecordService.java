package com.safetynet.alert.service;

import com.safetynet.alert.exception.MedicalRecordNotFoundException;
import com.safetynet.alert.model.MedicalRecord;

public interface IMedicalRecordService {
	
	/**
	 * Should calculate age based on birth date of a person
	 * @param firstName
	 * @param lastName
	 * @return Should return age in years or -1 if no medical record is found for this person
	 * @author Mathias Lauer
	 * 22 janv. 2021
	 * @throws MedicalRecordNotFoundException 
	 */
	public int getAgeOf(String firstName, String lastName) throws MedicalRecordNotFoundException;
	
	public int getAgeOf(MedicalRecord medicalRecord);
	
	/**
	 * Should return a medical record of a person
	 * @param firstName
	 * @param lastName
	 * @return MedicalRecord
	 * @author Mathias Lauer
	 * 28 janv. 2021
	 * @throws MedicalRecordNotFoundException 
	 */
	public MedicalRecord getMedicalRecordOf(String firstName, String lastName) throws MedicalRecordNotFoundException;
	
	/**
	 * Should return true if the person given is 18 years or under
	 * @param firstName
	 * @param lastName
	 * @return boolean
	 * @author Mathias Lauer
	 * 31 janv. 2021
	 */
	public boolean isChild(MedicalRecord medicalRecord);

	/**
	 * Can do some treatments before calling DAO to post a new medical record
	 * @param medicalRecord
	 * @return MedicalRecord was posted, null if something goes wrong
	 * @author Mathias Lauer
	 * 8 févr. 2021
	 */
	public MedicalRecord post(MedicalRecord medicalRecord);

	/**
	 * Can do some treatments before calling DAO to update a medical record
	 * @param medicalRecord, fields MedicalRecord.firstName and MedicalRecord.lastName are used to find the medical record to update, these fields are not updatable
	 * @return MedicalRecord was updated, null if something goes wrong
	 * @author Mathias Lauer
	 * 8 févr. 2021
	 */
	public MedicalRecord put(MedicalRecord medicalRecord);

	/**
	 * Can do some treatments before calling DAO to delete a medical record
	 * @param firstName
	 * @param lastName
	 * @return MedicalRecord was deleted, null if something goes wrong
	 * @author Mathias Lauer
	 * 8 févr. 2021
	 */
	public MedicalRecord delete(String firstName, String lastName);

}
