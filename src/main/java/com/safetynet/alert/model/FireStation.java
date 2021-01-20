package com.safetynet.alert.model;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class FireStation {
	
	private String address;
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
