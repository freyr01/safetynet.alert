package com.safetynet.alert.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ActuatorsTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testActuatorHealth_shouldReturnStatusOk() throws Exception {
		mockMvc.perform(get("/actuator/health"))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testActuatorTrace_shouldReturnStatusOk() throws Exception {
		mockMvc.perform(get("/actuator/httptrace"))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testActuatorInfo_shouldReturnStatusOk() throws Exception {
		mockMvc.perform(get("/actuator/info"))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testActuatorMetrics_shouldReturnStatusOk() throws Exception {
		mockMvc.perform(get("/actuator/metrics"))
		.andExpect(status().isOk());
	}

}
