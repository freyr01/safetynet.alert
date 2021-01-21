package com.safetynet.alert.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safetynet.alert.model.Person;

import java.util.List;

@Repository
public class PersonDAOImpl implements IPersonDAO {
	
	@Autowired
	DatabaseDAO db;
	
	public List<Person> findAll(){
		return db.getDb().getPersons();
	}

}
