package com.safetynet.alert.model;

/**
 * Represent a fire station mapping in database
 * @author Mathias Lauer
 * 6 févr. 2021
 */
public class FireStationMapping {
	
	private String address;
	private int station;
	
	public FireStationMapping(String address, int station) {
		this.address = address;
		this.station = station;
	}
	
	public FireStationMapping() {
		
	}
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
