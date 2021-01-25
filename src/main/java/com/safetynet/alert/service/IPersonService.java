package com.safetynet.alert.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.safetynet.alert.model.Person;

@Service
public interface IPersonService {
	
	public List<Person> getPersonByFullName(String firstName, String lastname);
	public List<String> getPersonEmailByCity(String city);
	public List<Object> getChildByAddress(String address);
	public Person add(Person person);

}
