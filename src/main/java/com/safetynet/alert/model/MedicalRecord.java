package com.safetynet.alert.model;

import java.util.List;

/**
 * Represent a medical record in database, fields are firstName, lastName, birth date, medications and allergies
 * @author Mathias Lauer
 * 6 févr. 2021
 */
public class MedicalRecord {
	
	private String firstName;
	private String lastName;
	private String birthdate;
	private List<String> medications;
	private List<String> allergies;

	public MedicalRecord(String firstName,String lastName,String birthdate, List<String> medications, List<String> allergies) {
		setFirstName(firstName);
		setLastName(lastName);
		setBirthdate(birthdate);
		setMedications(medications);
		setAllergies(allergies);
	}
	
	public MedicalRecord() {
		
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public List<String> getMedications() {
		return medications;
	}
	public void setMedications(List<String> medications) {
		this.medications = medications;
	}
	public List<String> getAllergies() {
		return allergies;
	}
	public void setAllergies(List<String> allergies) {
		this.allergies = allergies;
	}
}
