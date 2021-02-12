package com.safetynet.alert.dto;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class FloodStationDTO {
	
	private int station;
	private SortedMap<String, List<PersonInfoDTO>> address;
	
	public FloodStationDTO() {
		setAddress(new TreeMap<String, List<PersonInfoDTO>>());
	}
	
	public int getStation() {
		return station;
	}
	public void setStation(int station) {
		this.station = station;
	}

	public SortedMap<String, List<PersonInfoDTO>> getAddress() {
		return address;
	}

	public void setAddress(SortedMap<String, List<PersonInfoDTO>> address) {
		this.address = address;
	}



}
