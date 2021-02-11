package com.safetynet.alert.dto;

import java.util.List;

/**
 * Represent a list of person covered by a fire station with a count of adult and child number
 * @author Mathias Lauer
 * 6 févr. 2021
 */
public class FirestationDTO {
	
	private int adultCount;
	private int childCount;
	
	private List<FireStationCoveragePersonDTO> persons;

	public List<FireStationCoveragePersonDTO> getPersons() {
		return persons;
	}

	public void setPersons(List<FireStationCoveragePersonDTO> persons) {
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
