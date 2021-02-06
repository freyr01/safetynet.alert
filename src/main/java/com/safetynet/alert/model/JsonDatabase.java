package com.safetynet.alert.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

/**
 * Represent the Json Database, fields are fireStations, medicalRecords, persons
 * @author Mathias Lauer
 * 6 févr. 2021
 */
public class JsonDatabase {
	
	@JsonAlias("firestations") //Used to specify the var name in json file when is not identical as named here in Java
	private List<FireStationMapping> fireStations;
	
	@JsonAlias("medicalrecords")
	private List<MedicalRecord> medicalRecords;
	
	private List<Person> persons;
	
	public List<FireStationMapping> getFireStations() {
		return fireStations;
	}
	public void setFireStations(List<FireStationMapping> fireStations) {
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
