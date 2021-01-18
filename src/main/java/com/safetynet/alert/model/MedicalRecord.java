package com.safetynet.alert.model;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import lombok.Data;

@Data
@EntityScan
public class MedicalRecord {
	private String firstName;
	private String lastName;
	private String birthdate;
	private String[] medications;
	private String[] allergies;
}
