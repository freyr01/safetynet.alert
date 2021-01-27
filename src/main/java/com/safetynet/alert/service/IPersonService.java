package com.safetynet.alert.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.safetynet.alert.dto.ChildInfo;
import com.safetynet.alert.model.Person;

@Service
public interface IPersonService {
	
	public List<Person> getPersonByFullName(String firstName, String lastname);
	public List<String> getPersonEmailByCity(String city);
	public List<ChildInfo> getChildByAddress(String address);
	public Person add(Person person);
	
	/**
	 * Edit the first person found in database with the lastName and firstName given
	 * @param lastName
	 * @param firstName
	 * @param person new data of the person, firstName and lastName are not required in the object since the research is did with other args
	 * @return Person the edited object if editing succeed, null otherwise
	 * @author Mathias Lauer
	 * 26 janv. 2021
	 */
	public Person update(String lastName, String firstName, Person person);

}
