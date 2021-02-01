package com.safetynet.alert.service;

import com.safetynet.alert.dto.FireStationCoverage;
import com.safetynet.alert.model.Person;

import java.util.List;

public interface IFireStationService {

	/**
	 * Should return all persons that address is covered by the given station
	 * @param stationNumber
	 * @return List<Person>
	 * @author Mathias Lauer
	 * 1 févr. 2021
	 */
	public List<Person> getCoveredPersonOf(int stationNumber);
	
	/**
	 * Should return a DTO FireStationCoverage containing persons under cover and count informations.
	 * @param stationNumber
	 * @return FireStationCoverage  
	 * @author Mathias Lauer
	 * 1 févr. 2021
	 */
	public FireStationCoverage getFireStationCoverageFor(int stationNumber);

}
