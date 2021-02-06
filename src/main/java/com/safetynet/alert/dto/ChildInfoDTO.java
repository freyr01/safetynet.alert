package com.safetynet.alert.dto;

import java.util.List;

import com.safetynet.alert.model.Person;

/**
 * Represent a child information, his first name, last name, age and other family member(s)
 * @author Mathias Lauer
 * 6 févr. 2021
 */
public class ChildInfoDTO {
	
	private String firstName;
	private String lastName;
	private int age;
	private List<Person> famillyMember;
	
	public ChildInfoDTO(String p_firstName, String p_lastName, int p_age, List<Person> p_famillyMember) {
		setFirstName(p_firstName);
		setLastName(p_lastName);
		setAge(p_age);
		setFamillyMember(p_famillyMember);
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
	public List<Person> getFamillyMember() {
		return famillyMember;
	}
	public void setFamillyMember(List<Person> famillyMember) {
		this.famillyMember = famillyMember;
	}

}
