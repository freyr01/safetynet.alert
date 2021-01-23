package com.safetynet.alert.dao;

import java.util.List;

import com.safetynet.alert.model.Person;

public interface IPersonDAO {
	
	public List<Person> findAll();
	public List<Person> findByFullName(String firstName, String lastName);
	public List<Person> findByAddress(String address);
	public List<Person> findByCity(String city);
	public Person save(Person person);

}
