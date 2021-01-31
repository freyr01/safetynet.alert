package com.safetynet.alert.dto;

import java.util.List;

import com.safetynet.alert.model.Person;

public class FireStationCoverage {
	
	private int adultCount;
	private int childCount;
	
	private List<Person> persons;

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public int getAdultCount() {
		return adultCount;
	}

	public void setAdultCount(int adultCount) {
		this.adultCount = adultCount;
	}

	public int getChildCount() {
		return childCount;
	}

	public void setChildCount(int childCount) {
		this.childCount = childCount;
	}

}
