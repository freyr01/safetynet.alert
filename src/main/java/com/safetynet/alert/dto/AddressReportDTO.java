package com.safetynet.alert.dto;

import java.util.List;

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
