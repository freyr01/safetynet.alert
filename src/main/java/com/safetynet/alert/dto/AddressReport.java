package com.safetynet.alert.dto;

import java.util.List;

import com.safetynet.alert.model.Person;

public class AddressReport {
	
	private int stationNumber;
	private List<Person> person;
	public int getStationNumber() {
		return stationNumber;
	}
	public void setStationNumber(int stationNumber) {
		this.stationNumber = stationNumber;
	}
	public List<Person> getPerson() {
		return person;
	}
	public void setPerson(List<Person> person) {
		this.person = person;
	}

}
