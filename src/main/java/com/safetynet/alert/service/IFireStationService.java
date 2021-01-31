package com.safetynet.alert.service;

import com.safetynet.alert.dto.FireStationCoverage;

public interface IFireStationService {

	public FireStationCoverage getCoveredFolkOf(int stationNumber);

}
