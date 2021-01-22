package com.safetynet.alert.service;

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

}
