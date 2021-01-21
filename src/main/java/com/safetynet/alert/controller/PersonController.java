package com.safetynet.alert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alert.dao.PersonDAO;
import com.safetynet.alert.model.Person;

import java.util.List;

@RestController
public class PersonController {

	@Autowired
	private PersonDAO personDao;
	
	@GetMapping(value="/person")
	public List<Person> listPersons(){
		return personDao.findAll();
	}
	
}
