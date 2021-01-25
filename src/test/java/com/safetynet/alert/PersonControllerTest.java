package com.safetynet.alert;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alert.model.Person;
import com.safetynet.alert.service.PersonServiceImpl;

@SpringBootTest
//@WebMvcTest(controllers = PersonController.class)
@AutoConfigureMockMvc
public class PersonControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	PersonServiceImpl personService;
	
	@Test
	public void testEndpointGetCommunityEmail() throws Exception {
		mockMvc.perform(get("/communityEmail?city=?")).andExpect(status().isOk());
		verify(personService, Mockito.times(1)).getPersonEmailByCity(anyString());
	}
	
	@Test
	public void testEndpointGetPersonInfo() throws Exception {
		mockMvc.perform(get("/personInfo?firstName=?&lastName=?")).andExpect(status().isOk());
	}

	@Test
	public void testEndpointGetChildAlert() throws Exception {
		mockMvc.perform(get("/childAlert?address=?")).andExpect(status().isOk());
	}
	
	@Test
	public void testEndpointPostPerson() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post("/person")
				.content(asJsonString(new Person("Amanda", "Lir", "951 LoneTree Rd", "Culver", "97451", "0101010101", "amanada.lir@gmail.com")))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk());
	}
	
	private static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
