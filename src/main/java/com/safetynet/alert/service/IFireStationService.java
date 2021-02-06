package com.safetynet.alert.service;

import com.safetynet.alert.dto.AddressReportDTO;
import com.safetynet.alert.dto.AddressReportPersonDTO;
import com.safetynet.alert.dto.FireStationCoverageDTO;
import com.safetynet.alert.model.Person;

import java.util.List;
import java.util.Map;

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
	public FireStationCoverageDTO getFireStationCoverageFor(int stationNumber);
	
	/**
	 * Should return a list of phone number of persons covered by station given in param
	 * @param stationNumber
	 * @return List<String>
	 * @author Mathias Lauer
	 * 1 févr. 2021
	 */
	public List<String> getPhoneOfAllPersonCoveredBy(int stationNumber);

	/**
	 * Should return a FloodStationCoverageDTO containing all family covered by given stations number
	 * @param stationsNumber
	 * @return FloodStationCoveragDTO 
	 * @author Mathias Lauer
	 * 4 févr. 2021
	 */
	public List<AddressReportDTO> getFloodStationCoverageFor(List<Integer> stationsNumber);

}
