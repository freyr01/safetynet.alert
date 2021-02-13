package com.safetynet.alert.dto;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class FloodStationDTO {
	
	private int station;
	private SortedMap<String, List<PersonInfoDTO>> addresses;
	
	public FloodStationDTO() {
		setAddresses(new TreeMap<String, List<PersonInfoDTO>>());
	}
	
	public int getStation() {
		return station;
	}
	public void setStation(int station) {
		this.station = station;
	}

	public SortedMap<String, List<PersonInfoDTO>> getAddresses() {
		return addresses;
	}

	public void setAddresses(SortedMap<String, List<PersonInfoDTO>> addresses) {
		this.addresses = addresses;
	}



}
