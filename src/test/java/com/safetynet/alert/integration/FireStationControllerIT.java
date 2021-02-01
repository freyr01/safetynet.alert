
package com.safetynet.alert.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.alert.service.IFireStationService;

@SpringBootTest
@AutoConfigureMockMvc
public class FireStationControllerIT {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	IFireStationService fireStationService;
	
	@Test
	public void testEndpointFirestation_shouldReturnStatusIsOkWithBodyAdultCount5() throws Exception {
		mockMvc.perform(get("/firestation?stationNumber=1"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("adultCount", is(5)));
	}
	
	@Test
	public void testEndpointPhoneAlert_shouldReturnStatusOkWithFirstNumber() throws Exception {
		mockMvc.perform(get("/phoneAlert?firestation=1"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0]", is("841-874-6512")));
	}

}
