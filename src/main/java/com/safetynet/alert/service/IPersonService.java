package com.safetynet.alert.service;

import java.util.List;

import com.safetynet.alert.model.Person;

public interface IPersonService {
	
	public List<Person> getPersonByFullName(String firstName, String lastname);
	public List<String> getPersonEmailByCity(String city);
	public List<Object> getChildByAddress(String address);
	public Person add(String firstName, String lastName, String address, String city, String zip, String phone,
			String email);

}
