package com.safetynet.alert.dto;

/**
 * Represent a person covered by a fire station in FireStationCoverageDTO by his first name, last name, address and phone
 * @author Mathias Lauer
 * 6 févr. 2021
 */
public class FireStationCoveragePersonDTO {
	
	private String firstName;
	private String lastName;
	private String address;
	private String phone;
	
	public FireStationCoveragePersonDTO() {
		
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

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
