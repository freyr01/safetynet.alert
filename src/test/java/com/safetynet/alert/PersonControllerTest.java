package com.safetynet.alert;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.safetynet.alert.controller.PersonController;
import com.safetynet.alert.model.Person;
import com.safetynet.alert.service.PersonServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PersonControllerTest {

	@Mock
	private static PersonServiceImpl personService;
	
	private PersonController controller;
	
	@BeforeEach
	public void setUpPerTest() {
		controller = new PersonController(personService);
	}

	@Test
	public void testUpdateKnownUser_controllerShouldTransmitToServiceAndReturnStatusOKAndNotNullBody() {
		when(personService.update(anyString(), anyString(), any(Person.class))).thenReturn(new Person());
		
		ResponseEntity<Person> response = controller.updatePerson("", "", new Person());
		
		verify(personService, Mockito.times(1)).update(anyString(), anyString(), any(Person.class));
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
	}
	
	@Test
	public void testUpdatingUnknownUser_shouldReturnBadStatusAndNullBody() {
		when(personService.update(anyString(), anyString(), any(Person.class))).thenReturn(null);
		ResponseEntity<Person> response = controller.updatePerson("", "", new Person());
		
		verify(personService, Mockito.times(1)).update(anyString(), anyString(), any(Person.class));
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertNull(response.getBody());
	}
}
