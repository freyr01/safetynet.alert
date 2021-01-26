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
	
	@Autowired
	DatabaseDAO db;
	
	public List<Person> findAll(){
		return db.getDb().getPersons();
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
	
	public Person save(Person person) {
		db.getDb().getPersons().add(person);
		return person;
	}
	
	public Person update(String lastName, String firstName, Person newPersonDatas) {
		List<Person> persons = findByFullName(firstName, lastName);
		if(persons.size() < 1) {
			log.error("Cannot found anybody named: {} {}", firstName, lastName);
			return null;
		}
		
		Person personToEdit = persons.get(0);
		
		if(newPersonDatas.getAddress() != null && ! newPersonDatas.getAddress().isEmpty()) {
			personToEdit.setAddress(newPersonDatas.getAddress());
		}
		
		if(newPersonDatas.getCity() != null && ! newPersonDatas.getCity().isEmpty()) {
			personToEdit.setCity(newPersonDatas.getCity());
		}
		
		if(newPersonDatas.getEmail() != null && ! newPersonDatas.getEmail().isEmpty()) {
			personToEdit.setEmail(newPersonDatas.getEmail());
		}
		
		if(newPersonDatas.getZip() != null && ! newPersonDatas.getZip().isEmpty()) {
			personToEdit.setZip(newPersonDatas.getZip());
		}
		
		if(newPersonDatas.getPhone() != null && ! newPersonDatas.getPhone().isEmpty()) {
			personToEdit.setPhone(newPersonDatas.getPhone());
		}
		
		return personToEdit;
	}

}
