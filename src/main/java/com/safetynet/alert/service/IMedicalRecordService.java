package com.safetynet.alert.service;

public interface IMedicalRecordService {
	
	/**
	 * Calculate age based on birth date of a person
	 * @param firstName
	 * @param lastName
	 * @return Age in years
	 * @author Mathias Lauer
	 * 22 janv. 2021
	 */
	public int getAgeOf(String firstName, String lastName);

}
