package com.safetynet.alert.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.safetynet.alert.dto.AddressReportDTO;
import com.safetynet.alert.dto.AddressReportPersonDTO;
import com.safetynet.alert.dto.ChildInfoDTO;
import com.safetynet.alert.dto.PersonInfoDTO;
import com.safetynet.alert.model.Person;

@Service
public interface IPersonService {
	
	/**
	 * Should return a list of PersonInfo for given person firstName and lastName given
	 * @param firstName
	 * @param lastName
	 * @return List<PersonInfo>
	 * @author Mathias Lauer
	 * 2 févr. 2021
	 */
	public List<PersonInfoDTO> getPersonInfo(String firstName, String lastName);
	
	/**
	 * Should return a list of PersonInfo for given person lastName
	 * @param lastName
	 * @return List<PersonInfo>
	 * @author Mathias Lauer
	 * 2 févr. 2021
	 */
	public List<PersonInfoDTO> getPersonInfo(String lastName);
	
	/**
	 * Should return a list a mail of all persons from given city
	 * @param city
	 * @return List<String>
	 * @author Mathias Lauer
	 * 2 févr. 2021
	 */
	public List<String> getPersonEmailByCity(String city);
	
	/**
	 * Should return a list of ChildInfo who lives in given address
	 * @param address
	 * @return List<ChildInfo>
	 * @author Mathias Lauer
	 * 2 févr. 2021
	 */
	public List<ChildInfoDTO> getChildByAddress(String address);
	
	/**
	 * Should add a new Person in the system
	 * @param person to add
	 * @return Person object added
	 * @author Mathias Lauer
	 * 2 févr. 2021
	 */
	public Person add(Person person);
	
	/**
	 * Update the first person found in database with the lastName and firstName given
	 * @param lastName
	 * @param firstName
	 * @param person new data of the person, firstName and lastName are not required in the object since the research is did with args firstName and lastName
	 * @return Person the edited object if editing succeed, null otherwise
	 * @author Mathias Lauer
	 * 26 janv. 2021
	 */
	public Person update(String firstName, String lastName, Person person);
	
	/**
	 * Should delete the person named by given arguments
	 * @param firstName
	 * @param lastName
	 * @return Person object deleted
	 * @author Mathias Lauer
	 * 2 févr. 2021
	 */
	public Person delete(String firstName, String lastName);
	
	/**
	 * Should return an AddressReport object containing information of persons who lives at and fire station covering
	 * @param address
	 * @return AddressReport or null if address is not found
	 * @author Mathias Lauer
	 * 2 févr. 2021
	 */
	public AddressReportDTO getAddressReport(String address);
	
	/**
	 * Should return a list of AddressReportPersonDTO of all person living in address given
	 * @param address
	 * @return List<AddressReportPersonDTO>
	 * @author Mathias Lauer
	 * 5 févr. 2021
	 */
	public List<AddressReportPersonDTO> getAddressReportPersonDTO(String address);

}
