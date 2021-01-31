package com.safetynet.alert.service;

import com.safetynet.alert.model.MedicalRecord;

public interface IMedicalRecordService {
	
	/**
	 * Should calculate age based on birth date of a person
	 * @param firstName
	 * @param lastName
	 * @return Should return age in years or -1 if no medical record is found for this person
	 * @author Mathias Lauer
	 * 22 janv. 2021
	 */
	public int getAgeOf(String firstName, String lastName);
	
	public int getAgeOf(MedicalRecord medicalRecord);
	
	/**
	 * Should return a medical record of a person
	 * @param firstName
	 * @param lastName
	 * @return MedicalRecord
	 * @author Mathias Lauer
	 * 28 janv. 2021
	 */
	public MedicalRecord getMedicalRecordOf(String firstName, String lastName);
	
	/**
	 * Should return true if the person given is 18 years or under
	 * @param firstName
	 * @param lastName
	 * @return boolean
	 * @author Mathias Lauer
	 * 31 janv. 2021
	 */
	public boolean isChild(String firstName, String lastName);

}
