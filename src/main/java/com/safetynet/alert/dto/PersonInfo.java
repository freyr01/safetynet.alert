package com.safetynet.alert.dto;

import java.util.List;

public class PersonInfo {
	
	private String firstName;
	private String lastName;
	private int age;
	private String address;
	private List<String> medications;
	private List<String> allergies;
	
	public PersonInfo(String firstName, String lastName, int age, String address, List<String> medications,
			List<String> allergies) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.address = address;
		this.medications = medications;
		this.allergies = allergies;
	}
	
	public PersonInfo() {
		
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
