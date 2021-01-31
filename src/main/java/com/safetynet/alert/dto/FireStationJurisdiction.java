package com.safetynet.alert.dto;

import java.util.List;

public class FireStationJurisdiction {
	
	private int adult;
	private int child;
	
	private List<String> persons;

	public int getAdult() {
		return adult;
	}

	public void setAdult(int adult) {
		this.adult = adult;
	}

	public int getChild() {
		return child;
	}

	public void setChild(int child) {
		this.child = child;
	}

	public List<String> getPersons() {
		return persons;
	}

	public void setPersons(List<String> persons) {
		this.persons = persons;
	}

}
