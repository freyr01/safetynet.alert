package com.safetynet.alert.service;

import com.safetynet.alert.dto.FireStationJurisdiction;

public interface IFireStationService {

	FireStationJurisdiction getCoveredFolkOf(int stationNumber);

}
