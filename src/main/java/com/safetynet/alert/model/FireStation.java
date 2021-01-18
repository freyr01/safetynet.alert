package com.safetynet.alert.model;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import lombok.Data;

@Data
@EntityScan
public class FireStation {
	
	private String address;
	private int station;

}
