package com.safetynet.alert.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safetynet.alert.model.Person;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonDAOImpl implements IPersonDAO {
	
	static Logger log = LoggerFactory.getLogger(PersonDAOImpl.class);
	
	JsonDatabaseDAOImpl db;
	
	public PersonDAOImpl(@Autowired IDatabaseDAO p_db) {
		db = (JsonDatabaseDAOImpl)p_db;
	}
	
	public List<Person> findAll(){
		return db.getConnection().getPersons();
	}
	
	public List<Person> findByCity(String city) {
		List<Person> personByCity = new ArrayList<Person>();
		for(Person p : findAll()) {	//Browse list
			if(p.getCity().equals(city)) {	
				personByCity.add(p);
			}
		}
		
		return personByCity;
	}
	
	public List<Person> findByAddress(String address) {
		List<Person> personByAddress = new ArrayList<Person>();
		for(Person p : findAll()) {	//Browse list
			if(p.getAddress().equals(address)) {	
				personByAddress.add(p);
			}
		}
		
		return personByAddress;
	} 
	
	public List<Person> findByFullName(String firstName, String lastName) {
		List<Person> personByFullName = new ArrayList<Person>();
		for(Person p : findAll()) {	//Browse list
			if(p.getFirstName().equals(firstName) && p.getLastName().equals(lastName)) {	
				personByFullName.add(p);
			}
		}
		
		return personByFullName;
	} 
	
	@Override
	public List<Person> findByLastName(String lastName) {
		List<Person> persons = new ArrayList<Person>();
		for(Person p : findAll()) {
			if(p.getLastName().equals(lastName)) {
				persons.add(p);
			}
		}
		return persons;
	}
	
	public Person save(Person person) {
		if(db.getConnection().getPersons().add(person)) {
			return person;
		}
		
		return null;
		
	}
	
	public Person update(Person newPersonDatas) {
		List<Person> persons = findByFullName(newPersonDatas.getFirstName(), newPersonDatas.getLastName());
		if(persons.size() < 1) {
			log.error("Cannot found anybody named: {} {}", newPersonDatas.getFirstName(), newPersonDatas.getLastName());
			return null;
		}
		
		Person personToEdit = persons.get(0);
		
		if(newPersonDatas.getAddress() != null) {
			personToEdit.setAddress(newPersonDatas.getAddress());
		}
		
		if(newPersonDatas.getCity() != null) {
			personToEdit.setCity(newPersonDatas.getCity());
		}
		
		if(newPersonDatas.getEmail() != null) {
			personToEdit.setEmail(newPersonDatas.getEmail());
		}
		
		if(newPersonDatas.getZip() != null) {
			personToEdit.setZip(newPersonDatas.getZip());
		}
		
		if(newPersonDatas.getPhone() != null) {
			personToEdit.setPhone(newPersonDatas.getPhone());
		}
		
		return personToEdit;
	}

	@Override
	public Person delete(String firstName, String lastName) {
		for(Person p : findAll()) {
			if(p.getFirstName().equals(firstName) && p.getLastName().equals(lastName)) {	
				findAll().remove(p);
				return p;
			}
		}
		
		log.error("Not found person: {} {}", firstName, lastName);
		return null;
	}



}
