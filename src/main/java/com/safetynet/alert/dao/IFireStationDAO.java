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

}
