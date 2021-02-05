package com.safetynet.alert.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safetynet.alert.model.FireStationMapping;

@Repository
public class FireStationDAOImpl implements IFireStationDAO {
	
	private DatabaseDAO databaseDAO;
	
	public FireStationDAOImpl(@Autowired DatabaseDAO p_databaseDAO) {
		databaseDAO = p_databaseDAO;
	}

	@Override
	public List<FireStationMapping> findAll() {
		
		return databaseDAO.getDb().getFireStations();
	}

	@Override
	public List<FireStationMapping> findByStationsNumber(int[] stationsNumber) {
		List<FireStationMapping> fireStationMapping = databaseDAO.getDb().getFireStations();
		List<FireStationMapping> fireStationsFiltered = new ArrayList<FireStationMapping>();
		
		for(FireStationMapping mapping : fireStationMapping) {
			for(int i : stationsNumber) {
				if(mapping.getStation() == i) {
					fireStationsFiltered.add(mapping);
				}
			}
		}
		return fireStationsFiltered;
	}

	@Override
	public FireStationMapping findByAddress(String address) {
		
		for(FireStationMapping fireStationMapping : findAll()) {
			if(fireStationMapping.getAddress().equals(address)) {
				return fireStationMapping;
			}
		}
		return null;
	}

}
