package com.safetynet.alert;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.alert.controller.PersonController;
import com.safetynet.alert.service.PersonServiceImpl;

@WebMvcTest(controllers = PersonController.class)
public class PersonControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	PersonServiceImpl personService;
	
	@Test
	public void testEndpointCommunityEmail() throws Exception {
		mockMvc.perform(get("/communityEmail?city=?")).andExpect(status().isOk());
	}
	
	@Test
	public void testEndpointPersonInfo() throws Exception {
		mockMvc.perform(get("/personInfo?firstName=?&lastName=?")).andExpect(status().isOk());
	}

	@Test
	public void testEndpointChildAlert() throws Exception {
		mockMvc.perform(get("/childAlert?address=?")).andExpect(status().isOk());
	}
	
}
