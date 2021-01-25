package com.safetynet.alert.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Person {
	
	@NotNull @Min(3)
	private String firstName;
	
	@NotNull @Min(3)
	private String lastName;
	
	@NotNull @Min(7)
	private String address;
	
	@NotNull @Min(1)
	private String city;
	
	@NotNull @Min(3)
	private String zip;
	
	@NotNull @Min(4)
	private String phone;
	
	@NotNull @Email
	private String email;
	
	public Person(String p_firstName, String p_lastName, String p_address, String p_city, String p_zip, String p_phone, String p_email) {
		setFirstName(p_firstName);
		setLastName(p_lastName);
		setAddress(p_address);
		setCity(p_city);
		setZip(p_zip);
		setPhone(p_phone);
		setEmail(p_email);
	}
	
	public Person() {
		
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
