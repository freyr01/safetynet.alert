package com.safetynet.alert.dao;

import java.util.List;

import com.safetynet.alert.model.Person;

public interface IPersonDAO {
	
	public List<Person> findAll();

}
