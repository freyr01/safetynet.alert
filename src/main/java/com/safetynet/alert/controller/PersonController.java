package com.safetynet.alert.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alert.model.Person;
import com.safetynet.alert.service.PersonService;

@RestController
public class PersonController {

	@Autowired
	private PersonService personService;
	
	
	@GetMapping(value="/personInfo")
	public List<Person> getPersonByFullName(@RequestParam String firstName, @RequestParam String lastName) {
		return personService.getPersonByFullName(firstName, lastName);
	}
	
	@GetMapping(value="/communityEmail")
	public List<String> getPersonEmailByCity(@RequestParam String city){
		return personService.getPersonEmailByCity(city);
	}
 	
}
