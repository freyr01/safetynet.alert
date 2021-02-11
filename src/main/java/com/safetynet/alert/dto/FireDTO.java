package com.safetynet.alert.dto;

import java.util.List;

/**
 * Represent information of an address, the fire station that cover it and a list of person as AddressReportPersonDTO that lived 
 * @author Mathias Lauer
 * 6 févr. 2021
 */
public class FireDTO {
	
	private int stationNumber;
	
	private List<PersonInfoDTO> person;
	
	public int getStationNumber() {
		return stationNumber;
	}
	
	public void setStationNumber(int stationNumber) {
		this.stationNumber = stationNumber;
	}

	public List<PersonInfoDTO> getPerson() {
		return person;
	}

	public void setPerson(List<PersonInfoDTO> person) {
		this.person = person;
	}


}
