package com.safetynet.alert;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.alert.controller.FireStationController;
import com.safetynet.alert.service.IFireStationService;

@ExtendWith(MockitoExtension.class)
public class FireStationServiceTest {
	
	@Mock
	IFireStationService fireStationService;
	
	FireStationController fireStationController;
	
	@BeforeEach
	public void setUpPerTest() {
		fireStationController = new FireStationController(fireStationService);
	}
	
	@Test
	public void testControllerUseCorrectService() {
		
	}

}
