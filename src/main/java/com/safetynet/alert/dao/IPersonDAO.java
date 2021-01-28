package com.safetynet.alert.dao;

import java.util.List;

import com.safetynet.alert.model.Person;

public interface IPersonDAO {
	
	/**
	 * Get all person in the database
	 * @return Should return a List<Person>, if no data was found return an empty list
	 * @author Mathias Lauer
	 * 26 janv. 2021
	 */
	public List<Person> findAll();
	
	/**
	 * Get a list of person named as requested
	 * @param firstName
	 * @param lastName
	 * @return Should return a List<Person> for given full name, return an empty list if nobody founded
	 * @author Mathias Lauer
	 * 26 janv. 2021
	 */
	public List<Person> findByFullName(String firstName, String lastName);
	
	/**
	 * Get a list of person from an address
	 * @param address
	 * @return Should return a List<Person> for given address, return an empty list if nobody founded at this address
	 * @author Mathias Lauer
	 * 26 janv. 2021
	 */
	public List<Person> findByAddress(String address);
	
	/**
	 * Get a list of person from a city
	 * @param city
	 * @return Should return a list<Person> for the given city return an empty list if nobody was founded from this city
	 * @author Mathias Lauer
	 * 26 janv. 2021
	 */
	public List<Person> findByCity(String city);
	
	/**
	 * Add a new person in data base
	 * @param person will be added
	 * @return Should return the person object if adding was a success
	 * @author Mathias Lauer
	 * 26 janv. 2021
	 */
	public Person save(Person person);
	
	/**
	 * Update a person in database
	 * @param lastName
	 * @param fistName
	 * @param newPersonDatas
	 * @return Should return the modified person Object if succeed, null otherwise
	 * @author Mathias Lauer
	 * 26 janv. 2021
	 */
	public Person update(String lastName, String firstName, Person newPersonDatas);
	
	/**
	 * Delete a person in database
	 * @param lastName
	 * @param firstName
	 * @return Should return the person object was deleted, null otherwise
	 * @author Mathias Lauer
	 * 28 janv. 2021
	 */
	public Person delete(String lastName, String firstName);
}
