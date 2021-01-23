package com.safetynet.alert.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alert.model.Person;
import com.safetynet.alert.service.PersonServiceImpl;

@RestController
public class PersonController {
	
	private static Logger log = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	private PersonServiceImpl personService;
	
	
	@GetMapping(value="/personInfo")
	public List<Person> getPersonByFullName(@RequestParam String firstName, @RequestParam String lastName) {
		log.info("GET request /personInfo with param: firstName:{} lastName:{}", firstName, lastName);
		return personService.getPersonByFullName(firstName, lastName);
	}
	
	@GetMapping(value="/communityEmail")
	public List<String> getPersonEmailByCity(@RequestParam String city){
		return personService.getPersonEmailByCity(city);
	}
	
	@GetMapping(value = "/childAlert")
	public List<Object> getChildByAddress(@RequestParam String address){
		return personService.getChildByAddress(address);
	}
	
	@PostMapping(value = "/person")
	public Person addPerson(@RequestBody String firstName,
							@RequestBody String lastName,
							@RequestBody String address,
							@RequestBody String city,
							@RequestBody String zip,
							@RequestBody String phone, 
							@RequestBody String email) {
		
		return personService.add(firstName, lastName, address, city, zip, phone, email);
	}
	

}
