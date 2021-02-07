
package com.safetynet.alert.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alert.model.FireStationMapping;
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
	
	@Test
	public void testEndpointFloodStation_shouldReturnStatusOk() throws Exception {
		mockMvc.perform(get("/flood/stations?stations=1"))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testPostFireStation_shouldReturnStatusOk() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post("/firestation")
				.content(asJsonString(new FireStationMapping("5 ch de Vaugrenier", 3)))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk());
	}
	
	@Test
	public void testUpdateFireStation_shouldReturnStatusOk() throws Exception{
		FireStationMapping updatedStationMapping = new FireStationMapping("1509 Culver St", 2);
		mockMvc.perform(MockMvcRequestBuilders
				.put("/firestation")
				.content(asJsonString(updatedStationMapping))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk());	
	}
	
	@Test
	public void testUpdateFireStationWithUnknownTarget_shouldReturnStatusNotFound() throws Exception{
		FireStationMapping updatedStationMapping = new FireStationMapping("1509 Culvzeaeaer St", 2);
		mockMvc.perform(MockMvcRequestBuilders
				.put("/firestation")
				.content(asJsonString(updatedStationMapping))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isNotFound());	
	}
	
	@Test
	public void testDeleteFireStation_shouldReturnStatusOk() throws Exception {
		FireStationMapping deleteStationMapping = new FireStationMapping("1509 Culver St", 2);
		mockMvc.perform(MockMvcRequestBuilders
				.delete("/firestation")
				.content(asJsonString(deleteStationMapping))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk());	
	}
	
	@Test
	public void testDeleteUnknownFireStation_shouldReturnStatusNotFound() throws Exception {
		FireStationMapping deleteStationMapping = new FireStationMapping("1509 Culvazeazeer St", 2);
		mockMvc.perform(MockMvcRequestBuilders
				.delete("/firestation")
				.content(asJsonString(deleteStationMapping))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isNotFound());	
	}
	
	private static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

}
