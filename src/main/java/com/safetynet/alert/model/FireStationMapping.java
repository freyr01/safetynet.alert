package com.safetynet.alert.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class FireStationMapping {
	
	@NotNull @Min(5)
	private String address;
	
	@NotNull @Min(1)
	private int station;
	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getStation() {
		return station;
	}
	public void setStation(int station) {
		this.station = station;
	}

}
