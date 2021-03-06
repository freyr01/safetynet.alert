package com.safetynet.alert.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safetynet.alert.model.FireStationMapping;

@Repository
public class FireStationDAOImpl implements IFireStationDAO {
	
	static Logger log = LoggerFactory.getLogger(FireStationDAOImpl.class);
	private JsonDatabaseDAOImpl databaseDAO;
	
	public FireStationDAOImpl(@Autowired JsonDatabaseDAOImpl p_databaseDAO) {
		databaseDAO = p_databaseDAO;
	}

	@Override
	public List<FireStationMapping> findAll() {
		
		return databaseDAO.getConnection().getFireStations();
	}

	@Override
	public List<FireStationMapping> findByStationsNumber(List<Integer> stationsNumber) {
		List<FireStationMapping> fireStationMapping = databaseDAO.getConnection().getFireStations();
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
	public List<FireStationMapping> findByAddress(String address) {
		List<FireStationMapping> fireStationMappingList = new ArrayList<FireStationMapping>();
		for(FireStationMapping fireStationMapping : findAll()) {
			if(fireStationMapping.getAddress().equals(address)) {
				fireStationMappingList.add(fireStationMapping);
			}
		}
		return fireStationMappingList;
	}

	@Override
	public FireStationMapping post(FireStationMapping fireStation) {
		if(findAll().add(fireStation)) {
			return fireStation;
		}
		
		return null;
	}

	@Override
	public FireStationMapping update(FireStationMapping fireStation) {
		for(FireStationMapping fsm : findAll()) {
			if(fsm.getAddress().equals(fireStation.getAddress())) {
				if(fireStation.getStation() != 0) {
					fsm.setStation(fireStation.getStation());
					return fsm;
				}
			}
		}
		
		return null;
	}

	@Override
	public FireStationMapping delete(String address, int stationId) {
		for(FireStationMapping fsm : findAll()) {
			if(fsm.getAddress().equals(address) && fsm.getStation() == stationId) {
				if(findAll().remove(fsm)) {
					return fsm;
				}
			}
		}
		return null;
	}

}
