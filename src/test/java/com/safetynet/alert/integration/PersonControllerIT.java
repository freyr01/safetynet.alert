package com.safetynet.alert.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
	public void testEndpointGetChildAlert_shouldReturnStatusIsOk() throws Exception {
		mockMvc.perform(get("/childAlert?address=?")).andExpect(status().isOk());
	}
	
	@Test
	public void testAddingNewPerson_shouldReturnStatusIsCreated() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post("/person")
				.content(asJsonString(new Person("Amanda", "Lir", "951 LoneTree Rd", "Culver", "97451", "0101010101", "amanada.lir@gmail.com")))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isCreated());
		
		mockMvc.perform(get("/personInfo?firstName=Amanda&lastName=Lir")).andExpect(status().isOk()).andExpect(jsonPath("$[0].firstName", is("Amanda")));
	}
	
	@Test
	public void testUpdatingPerson_shouldReturnStatusOk() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post("/person")
				.content(asJsonString(new Person("Amanda", "Lir", "951 LoneTree Rd", "Culver", "97451", "0101010101", "amanada.lir@gmail.com")))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isCreated());
		
		mockMvc.perform(MockMvcRequestBuilders
				.put("/person?firstName=Amanda&lastName=Lir")
				.content(asJsonString(new Person("Amanda", "Lir", "5 Ch de Vaugrenier", "Antibes", "06600", "0101010101", "amanada.lir@gmail.com")))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk());
		
		mockMvc.perform(get("/personInfo?firstName=Amanda&lastName=Lir")).andExpect(status().isOk()).andExpect(jsonPath("$[0].address", is("5 Ch de Vaugrenier")));
		
		mockMvc.perform(delete("/person?firstName=Amanda&lastName=Lir")).andExpect(status().isOk());
	}
	
	
	
	private static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
