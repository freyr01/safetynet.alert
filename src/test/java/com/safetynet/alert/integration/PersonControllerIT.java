package com.safetynet.alert.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
import com.safetynet.alert.model.Person;
import com.safetynet.alert.service.IPersonService;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerIT {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	IPersonService personService;
	
	@Test
	public void testEndpointGetCommunityEmail_shouldReturnStatusIsOk() throws Exception {
		mockMvc.perform(get("/communityEmail?city=?")).andExpect(status().isOk());
	}

	@Test
	public void testEndpointGetChildAlert_shouldReturnStatusIsOk() throws Exception {
		mockMvc.perform(get("/childAlert?address=?")).andExpect(status().isOk());
	}
	
	@Test
	public void testEndpointGetAddressReport_shouldReturnStatusIsOk() throws Exception {
		mockMvc.perform(get("/fire?address=951 LoneTree Rd")).andExpect(status().isOk());
	}
	
	@Test
	public void testAddingNewPerson_shouldReturnStatusIsCreated() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post("/person")
				.content(asJsonString(new Person("Amanda", "Lir", "951 LoneTree Rd", "Culver", "97451", "0101010101", "amanada.lir@gmail.com")))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isCreated());
	}
	
	@Test
	public void testGetPersonInfo_shouldReturnPersonInfo() throws Exception {
		mockMvc.perform(get("/personInfo?firstName=Jonanathan&lastName=Marrack"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].address", is("29 15th St")))
		.andExpect(jsonPath("$[0].age", is(32)));
		
	}
	
	@Test
	public void testUpdatingPerson_shouldReturnStatusOk() throws Exception {

		Person newData = new Person();
		newData.setFirstName("John");
		newData.setLastName("Boyd");
		newData.setPhone("0102030405");
		mockMvc.perform(MockMvcRequestBuilders
				.put("/person")
				.content(asJsonString(newData))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk());	
	}
	
	@Test
	public void testDeletingPerson_shouldReturnStatusOk() throws Exception {
		mockMvc.perform(delete("/person?firstName=Jacob&lastName=Boyd")).andExpect(status().isOk());
	}
	
	
	
	private static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
