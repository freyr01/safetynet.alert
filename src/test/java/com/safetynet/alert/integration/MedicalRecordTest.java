package com.safetynet.alert.integration;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alert.model.MedicalRecord;
import com.safetynet.alert.service.IMedicalRecordService;

@SpringBootTest
@AutoConfigureMockMvc
public class MedicalRecordTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private IMedicalRecordService medicalRecordService;
	
	@Test
	public void testEndPointPostMedicalRecord_shouldReturnStatusOk() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post("/medicalRecord")
				.content(asJsonString(new MedicalRecord("Bobby","Bard","02/18/1994", Arrays.asList("tetracyclaz:650mg", "terazine:10mg"), Arrays.asList("peanut", "shellfish", "aznol"))))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk());
	}
	
	@Test
	public void testEndPointPostExistingMedicalRecord_shouldReturnStatusNoContent() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post("/medicalRecord")
				.content(asJsonString(new MedicalRecord("John","Boyd","02/18/1994", Arrays.asList("tetracyclaz:650mg", "terazine:10mg"), Arrays.asList("peanut", "shellfish", "aznol"))))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isNoContent());
	}
	
	@Test
	public void testEndPointUpdateMedicalRecord_shouldReturnStatusOk() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.put("/medicalRecord")
				.content(asJsonString(new MedicalRecord("John","Boyd","02/18/1994", Arrays.asList("tetracyclaz:650mg", "terazine:10mg"), Arrays.asList("peanut", "shellfish", "aznol"))))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk());
	}
	
	@Test
	public void testEndPointUpdateUnknownMedicalRecord_shouldReturnStatusNotFound() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.put("/medicalRecord")
				.content(asJsonString(new MedicalRecord("Joaze","Boydazez","02/18/1994", Arrays.asList("tetracyclaz:650mg", "terazine:10mg"), Arrays.asList("peanut", "shellfish", "aznol"))))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isNotFound());
	}
	
	@Test
	public void testEndPointDeleteMedicalRecord_shouldReturnStatusOk() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.delete("/medicalRecord").param("firstName", "Lily").param("lastName", "Cooper")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk());
	}
	
	@Test
	public void testEndPointDeleteMedicalRecord_shouldReturnStatusNotFound() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.delete("/medicalRecord").param("firstName", "Johaze").param("lastName", "Boydaze")
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
