package com.safetynet.alert.service;

import java.util.List;

import com.safetynet.alert.dto.FirestationDTO;
import com.safetynet.alert.dto.FloodStationDTO;
import com.safetynet.alert.model.FireStationMapping;
import com.safetynet.alert.model.Person;

public interface IFireStationService {

	/**
	 * Should return all Fire Station Mapping for address given
	 * @param address
	 * @return List<FireStationMapping>
	 * @author Mathias Lauer
	 * 13 févr. 2021
	 */
	public List<FireStationMapping> getByAddress(String address);
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
	public FirestationDTO getFireStationCoverageFor(int stationNumber);
	
	/**
	 * Should return a list of phone number of persons covered by station given, there should be no duplicate
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
	public List<FloodStationDTO> getFloodStationCoverageFor(List<Integer> stationsNumber);

	/**
	 * Add a new FireStationMapping in data base
	 * Can do some treatments here before adding in database
	 * @param fireStation will be added
	 * @return fireStation added or null if something goes wrong
	 * @author Mathias Lauer
	 * 7 févr. 2021
	 */
	public FireStationMapping post(FireStationMapping fireStation);

	/**
	 * Update a fire station mapping in database
	 * Can do some treatments here before updating database
	 * @param fireStation to update, address in the object is used to found the target fire station mapping to update
	 * @return FireStationMapping was updated or null if something goes wrong
	 * @author Mathias Lauer
	 * 7 févr. 2021
	 */
	public FireStationMapping put(FireStationMapping firestation);
	
	/**
	 * Delete a fire station mapping in database
	 * Can do some treatments here before deleting
	 * @param address of fire station to delete, the address field is used as unique field to find mapping to delete
	 * @return FireStationMapping object deleted or null if something goes wrong
	 * @author Mathias Lauer
	 * 7 févr. 2021
	 */
	public FireStationMapping delete(String address, int stationId);

}
