package com.safetynet.alert.model;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class MedicalRecord {
	private String firstName;
	private String lastName;
	private String birthdate;
	private String[] medications;
	private String[] allergies;
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
	public String[] getMedications() {
		return medications;
	}
	public void setMedications(String[] medications) {
		this.medications = medications;
	}
	public String[] getAllergies() {
		return allergies;
	}
	public void setAllergies(String[] allergies) {
		this.allergies = allergies;
	}
}
