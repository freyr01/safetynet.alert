package com.safetynet.alert.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Database {
	
	@JsonAlias("firestations")
	private List<FireStation> fireStations;
	@JsonAlias("medicalrecords")
	private List<MedicalRecord> medicalRecords;
	private List<Person> persons;
	
	public List<FireStation> getFireStations() {
		return fireStations;
	}
	public void setFireStations(List<FireStation> fireStations) {
		this.fireStations = fireStations;
	}
	public List<MedicalRecord> getMedicalRecords() {
		return medicalRecords;
	}
	public void setMedicalRecords(List<MedicalRecord> medicalRecords) {
		this.medicalRecords = medicalRecords;
	}
	public List<Person> getPersons() {
		return persons;
	}
	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

}
