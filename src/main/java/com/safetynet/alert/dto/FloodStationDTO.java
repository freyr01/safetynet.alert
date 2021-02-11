package com.safetynet.alert.dto;

import java.util.List;

public class FloodStationDTO {
	
	private String address;
	private List<PersonInfoDTO> personInfoDTO;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<PersonInfoDTO> getPersonInfoDTO() {
		return personInfoDTO;
	}
	public void setPersonInfoDTO(List<PersonInfoDTO> personInfoDTO) {
		this.personInfoDTO = personInfoDTO;
	}

}
