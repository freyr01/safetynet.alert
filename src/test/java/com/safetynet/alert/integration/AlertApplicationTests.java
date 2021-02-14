package com.safetynet.alert.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alert.model.FireStationMapping;
import com.safetynet.alert.model.MedicalRecord;
import com.safetynet.alert.model.Person;

@SpringBootTest
@AutoConfigureMockMvc
class AlertApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testCompleteProcess() throws Exception {
		final String FIRSTNAME = "Clement";
		final String LASTNAME = "Hoult";
		final String MAIL = "clement.hoult@gmail.com";
		
		//Add a person
		mockMvc.perform(MockMvcRequestBuilders
				.post("/person")
				.content(asJsonString(new Person(FIRSTNAME, LASTNAME, "25 Ch du Gretor", "Nice", "06200", "0101010101", MAIL)))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isCreated());
		
		//Update the person
		Person newData = new Person();
		newData.setFirstName(FIRSTNAME);
		newData.setLastName(LASTNAME);
		newData.setPhone("0202020202");
		
		mockMvc.perform(MockMvcRequestBuilders
				.put("/person")
				.content(asJsonString(newData))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk());
		
		//Add his medical record
		mockMvc.perform(MockMvcRequestBuilders
				.post("/medicalRecord")
				.content(asJsonString(new MedicalRecord(FIRSTNAME,LASTNAME,"02/18/1994", Arrays.asList("tetracyclaz:650mg", "terazine:10mg"), Arrays.asList("peanut", "shellfish", "aznol"))))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk());
		
		//Update his medical record
		mockMvc.perform(MockMvcRequestBuilders
				.put("/medicalRecord")
				.content(asJsonString(new MedicalRecord(FIRSTNAME,LASTNAME,"02/18/1982", null, null)))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk());
		
		//Add a fire station covering of his address
		mockMvc.perform(MockMvcRequestBuilders
				.post("/firestation")
				.content(asJsonString(new FireStationMapping("25 Ch du Gretor", 99)))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk());
		
		//Get the person informations, check some data like age depending of his medical record
		mockMvc.perform(get("/personInfo?firstName=" + FIRSTNAME + "&lastName=" + LASTNAME))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].address", is("25 Ch du Gretor")))
		.andExpect(jsonPath("$[0].allergies[0]", is("peanut")))
		.andExpect(jsonPath("$[0].medications[0]", is("tetracyclaz:650mg")))
		.andExpect(jsonPath("$[0].age", is(38)));
		
		//Get a the fire station coverage and check number of adult covered
		mockMvc.perform(get("/firestation?stationNumber=99"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("adultCount", is(1)));
		
		//Get phone list covered by the fire station, check the first occurrence
		mockMvc.perform(get("/phoneAlert?firestation=99"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0]", is("0202020202")));
		
		//Get the mail list of a city, check the first occurrence
		mockMvc.perform(get("/communityEmail?city=Nice")).andExpect(status().isOk())
		.andExpect(jsonPath("$[0]", is(MAIL)));
		
		//Delete all data
		mockMvc.perform(delete("/person?firstName=" + FIRSTNAME + "&lastName=" + LASTNAME)).andExpect(status().isOk());
		mockMvc.perform(MockMvcRequestBuilders.delete("/medicalRecord")
				.param("firstName", FIRSTNAME)
				.param("lastName", LASTNAME))
		.andExpect(status().isOk());
		mockMvc.perform(MockMvcRequestBuilders
				.delete("/firestation").param("address", "25 Ch du Gretor").param("stationId", "99")
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

