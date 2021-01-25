package com.safetynet.alert.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class MedicalRecord {
	
	@NotNull @Min(3)
	private String firstName;
	
	@NotNull @Min(3)
	private String lastName;
	
	@NotNull @Past
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
