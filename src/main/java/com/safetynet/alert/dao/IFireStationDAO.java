package com.safetynet.alert.dao;

import java.util.List;

import com.safetynet.alert.model.FireStationMapping;

public interface IFireStationDAO {
	
	/**
	 * Should return all station mapping
	 * @return List<FireStation>
	 * @author Mathias Lauer
	 * 31 janv. 2021
	 */
	public List<FireStationMapping> findAll();
	
	/**
	 * Should return all mapping linked to station number given
	 * @param stationNumber 
	 * @return List<FireStation>
	 * @author Mathias Lauer
	 * 31 janv. 2021
	 */
	public List<FireStationMapping> findByStationsNumber(List<Integer> stationsNumber);
	
	/**
	 * Should return the FireStationMapping of the station covering address given
	 * @param address
	 * @return FireStationMapping found, null if not found
	 * @author Mathias Lauer
	 * 2 févr. 2021
	 */
	public FireStationMapping findByAddress(String address);

	/**
	 * Should add a new FireStationMapping in data base
	 * @param fireStation
	 * @return FireStationMapping added or null if something goes wrong
	 * @author Mathias Lauer
	 * 7 févr. 2021
	 */
	public FireStationMapping post(FireStationMapping fireStation);
	
	/**
	 * Should update the FireStationMapping given in param,
	 * only the station number field can be updated,
	 * existing mapping is found based on the address present in the object
	 * @param fireStation
	 * @return FireStationMapping updated or null if something goes wrong
	 * @author Mathias Lauer
	 * 7 févr. 2021
	 */
	public FireStationMapping update(FireStationMapping fireStation);
	
	/**
	 * Should delete the FireStationMapping found by the address given
	 * @param address is used as unique field to find the mapping to delete
	 * @return FireStationMapping deleted or null if something goes wrong
	 * @author Mathias Lauer
	 * 7 févr. 2021
	 */
	public FireStationMapping delete(String address);

}
