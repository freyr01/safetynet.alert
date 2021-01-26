package com.safetynet.alert.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alert.model.Person;
import com.safetynet.alert.service.PersonServiceImpl;

@SpringBootTest
//@WebMvcTest(controllers = PersonController.class)
@AutoConfigureMockMvc
public class PersonControllerIT {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	PersonServiceImpl personService;
	
	@Test
	public void testEndpointGetCommunityEmail_shouldReturnStatusIsOk() throws Exception {
		mockMvc.perform(get("/communityEmail?city=?")).andExpect(status().isOk());
	}
	
	@Test
	public void testEndpointGetPersonInfo_shouldReturnStatusIsOk() throws Exception {
		mockMvc.perform(get("/personInfo?firstName=?&lastName=?")).andExpect(status().isOk());
	}

	@Test
	public void testEndpointGetChildAlert_shouldReturnStatusIsOk() throws Exception {
		mockMvc.perform(get("/childAlert?address=?")).andExpect(status().isOk());
	}
	
	@Test
	public void testEndpointPostPerson_shouldReturnStatusIsCreated() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post("/person")
				.content(asJsonString(new Person("Amanda", "Lir", "951 LoneTree Rd", "Culver", "97451", "0101010101", "amanada.lir@gmail.com")))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isCreated());
	}
	
	private static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
