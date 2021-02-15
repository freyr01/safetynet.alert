package com.safetynet.alert.controller;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.safetynet.alert.dto.FireDTO;
import com.safetynet.alert.dto.ChildInfoDTO;
import com.safetynet.alert.dto.PersonInfoDTO;
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
	public ResponseEntity<MappingJacksonValue> getPersonByFullName(@RequestParam(required=false) String firstName,
			@RequestParam String lastName) {
		log.info("GET request /personInfo with param: firstName:{} lastName:{}", firstName, lastName);
		List<PersonInfoDTO> persons = personService.getPersonInfo(firstName, lastName);
		if(persons.size() < 1) {
			log.debug("Error getting person: {} {}", firstName, lastName);
			return ResponseEntity.notFound().build();
		}
		log.info("Return person list by fullname: {}", persons);
		return ResponseEntity.ok(applyPersoninfoExcludeFilter(persons, "phone"));
	}
	
	@GetMapping(value = "/childAlert")
	public List<ChildInfoDTO> getChildByAddress(@RequestParam String address){
		log.info("GET request /childAlert with param: address: {}", address);
		List<ChildInfoDTO> childByAddress = personService.getChildByAddress(address);
		log.info("Return child list by address: {}", childByAddress);
		return childByAddress; 
	}
	
	@GetMapping(value="fire")
	public ResponseEntity<MappingJacksonValue> getAddressReport(@RequestParam String address) {
		log.info("GET request /fire with param: address: {}", address);
		FireDTO fireDTO = personService.getFireDTO(address);
		if(fireDTO == null) {
			log.error("No result found for address: {}", address);
			return ResponseEntity.notFound().build();
		}
		
		log.info("Return fireDTO object: {}", fireDTO);
		return ResponseEntity.ok(applyPersoninfoExcludeFilter(fireDTO, "address", "email"));
		
	}
	
	@GetMapping(value="/communityEmail")
	public List<String> getPersonsEmailByCity(@RequestParam String city){
		log.info("GET request /communityEmail with param: city: {}", city);
		List<String> personsEmailByCity = personService.getPersonEmailByCity(city);
		log.info("Return email list of person by city: {}", personsEmailByCity);
		return personsEmailByCity;
	}
	
	public static MappingJacksonValue applyPersoninfoExcludeFilter(Object object, String... fieldExclude) {
		
		 SimpleBeanPropertyFilter personInfoFilter = SimpleBeanPropertyFilter.serializeAllExcept(fieldExclude);
	     FilterProvider filterList = new SimpleFilterProvider().addFilter("personInfoFilter", personInfoFilter);

	     if(object == null) {
	    	 log.error("Object given is null");
	    	 return null;
	     }
	     MappingJacksonValue objectFiltered = new MappingJacksonValue(object);

	     objectFiltered.setFilters(filterList);
	     
	     return objectFiltered;
	}
	
	//------- CRUD PART -----------
	@PostMapping(value = "/person")
	public ResponseEntity<Person> addPerson(@RequestBody Person person) {
		log.info("POST request /person with param: {}", person);
		Person personAdded = personService.add(person);
		if(personAdded == null) {
			log.error("Error while adding person: {}", person);
			return ResponseEntity.noContent().build();
		}
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/personInfo")
				.queryParam("firstName", person.getFirstName())
				.queryParam("lastName", person.getLastName())
				.build().toUri();
		
		log.info("Return response code created at location: {}", location);
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping(value="/person")
	public ResponseEntity<Person> updatePerson(@RequestBody Person person) {
		log.info("PUT request /person with Person object: {}",  person);
		Person editedPerson = personService.update(person);
		if(editedPerson == null) {
			log.error("No person found for full name: {} {}", person.getFirstName(), person.getLastName());
			return ResponseEntity.notFound().build();	
		}
		
		log.info("Return response code OK");
		
		return ResponseEntity.ok().body(editedPerson);
	}
	
	@DeleteMapping(value="/person")
	public ResponseEntity<Person> deletePerson(@RequestParam String firstName, @RequestParam String lastName) { 
		log.info("DELETE request /person with param: firstName: {}, lastName: {}", firstName, lastName);
		Person deletedPerson = personService.delete(firstName, lastName);
		if(deletedPerson == null) {
			log.error("No person found for full name: {} {}", firstName, lastName);
			return ResponseEntity.notFound().build();
		}
		
		log.info("Return response code OK");
		return ResponseEntity.ok().body(deletedPerson);
		
	}
	

}
