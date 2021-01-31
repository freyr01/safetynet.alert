package com.safetynet.alert.dao;

import java.util.List;

import com.safetynet.alert.model.FireStationMapping;

public interface IFireStationDAO {
	
	/**
	 * Return all station mapping
	 * @return List<FireStation>
	 * @author Mathias Lauer
	 * 31 janv. 2021
	 */
	public List<FireStationMapping> findAll();
	
	/**
	 * Return all mapping linked to station number arg
	 * @param stationNumber 
	 * @return List<FireStation>
	 * @author Mathias Lauer
	 * 31 janv. 2021
	 */
	public List<FireStationMapping> findByStationNumber(int stationNumber);

}
