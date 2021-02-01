package com.safetynet.alert;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.alert.controller.FireStationController;
import com.safetynet.alert.service.IFireStationService;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FireStationControllerTest {
	
	@Mock
	IFireStationService fireStationService;
	
	FireStationController fireStationController;
	
	@BeforeEach
	public void setUpPerTest() {
		fireStationController = new FireStationController(fireStationService);
	}
	
	@Test
	public void testEndpointFirestation_shouldCallServiceAndLog() {
		when(fireStationService.getFireStationCoverageFor(anyInt())).thenReturn(null);
		fireStationController.getCoveredPersonOf(1);
		
		verify(fireStationService, Mockito.times(1)).getFireStationCoverageFor(1);
		
	}
	

}
