package com.safetynet.alert.dto;

import java.util.List;

/**
 * Represent information of an address, the fire station that cover it and a list of person as AddressReportPersonDTO that lived 
 * @author Mathias Lauer
 * 6 févr. 2021
 */
public class AddressReportDTO {
	
	private int stationNumber;
	private List<AddressReportPersonDTO> person;
	
	public int getStationNumber() {
		return stationNumber;
	}
	
	public void setStationNumber(int stationNumber) {
		this.stationNumber = stationNumber;
	}
	
	public List<AddressReportPersonDTO> getPerson() {
		return person;
	}
	
	public void setPerson(List<AddressReportPersonDTO> person) {
		this.person = person;
	}

}
