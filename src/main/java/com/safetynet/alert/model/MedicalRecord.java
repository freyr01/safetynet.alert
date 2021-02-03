package com.safetynet.alert.model;

import java.util.List;

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
	
	private List<String> medications;
	
	private List<String> allergies;
	

	
	public MedicalRecord(@NotNull @Min(3) String firstName, @NotNull @Min(3) String lastName,
			@NotNull @Past String birthdate, List<String> medications, List<String> allergies) {
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
