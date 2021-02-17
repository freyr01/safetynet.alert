package com.safetynet.alert.dto;

import java.util.List;

/**
 * Represent information of an address, the fire stations that cover it and a list of person living at this address
 * @author Mathias Lauer
 * 6 févr. 2021
 */
public class FireDTO {
	
	private List<Integer> stationNumber;
	
	private List<PersonInfoDTO> person;
	
	public List<Integer> getStationNumber() {
		return stationNumber;
	}
	
	public void setStationNumber(List<Integer> stationNumber) {
		this.stationNumber = stationNumber;
	}

	public List<PersonInfoDTO> getPerson() {
		return person;
	}

	public void setPerson(List<PersonInfoDTO> person) {
		this.person = person;
	}


}
