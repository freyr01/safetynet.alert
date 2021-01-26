package com.safetynet.alert.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.safetynet.alert.model.Person;
import com.safetynet.alert.service.IPersonService;

@RestController
public class PersonController {
	
	private static Logger log = LoggerFactory.getLogger(PersonController.class);
	
	IPersonService personService;
	
	public PersonController(@Autowired IPersonService p_personService) {
		personService = p_personService;
	}
	
	@GetMapping(value="/personInfo")
	public List<Person> getPersonByFullName(@RequestParam String firstName, @RequestParam String lastName) {
		log.info("GET request /personInfo with param: firstName:{} lastName:{}", firstName, lastName);
		List<Person> personsByFullName = personService.getPersonByFullName(firstName, lastName);
		log.info("Return person list by fullname: {}", personsByFullName);
		return  personsByFullName;
	}
	
	@GetMapping(value="/communityEmail")
	public List<String> getPersonsEmailByCity(@RequestParam String city){
		log.info("GET request /communityEmail with param: city: {}", city);
		List<String> personsEmailByCity = personService.getPersonEmailByCity(city);
		log.info("Return email list of person by city: {}", personsEmailByCity);
		return personsEmailByCity;
	}
	
	@GetMapping(value = "/childAlert")
	public List<Object> getChildByAddress(@RequestParam String address){
		log.info("GET request /childAlert with param: address: {}", address);
		List<Object> childByAddress = personService.getChildByAddress(address);
		log.info("Return child list by address: {}", childByAddress);
		return childByAddress; 
	}
	
	@PostMapping(value = "/person")
	public ResponseEntity<Void> addPerson(@RequestBody Person person) {
		log.info("POST request /person with param: {}", person);
		Person personAdded = personService.add(person);
		if(personAdded == null) {
			log.error("Error while adding person: {}", person);
			return ResponseEntity.noContent().build();
		}
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{lastName}/{firstName}")
				.buildAndExpand(new Object[] {person.getLastName(), person.getFirstName()}).toUri();
		
		log.info("Return response code created at location: {}", location);
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping(value="/person/{lastName}/{firstName}")
	public ResponseEntity<Person> updatePerson(@PathVariable(value = "lastName") String lastName,
											@PathVariable(value = "firstName") String firstName,
											@RequestBody Person person) {
		
		Person editedPerson = personService.update(lastName, firstName, person);
		if(editedPerson == null) {
			log.error("No person found for full name: {} {}", firstName, lastName);
			return ResponseEntity.notFound().build();	
		}
		
		log.info("Return response code OK");
		
		return ResponseEntity.ok().body(editedPerson);
	}
	

}
