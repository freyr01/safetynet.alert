package com.safetynet.alert;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.alert.dao.IFireStationDAO;
import com.safetynet.alert.dao.IPersonDAO;
import com.safetynet.alert.dto.FireDTO;
import com.safetynet.alert.dto.PersonInfoDTO;
import com.safetynet.alert.dto.ChildInfoDTO;
import com.safetynet.alert.exception.MedicalRecordNotFoundException;
import com.safetynet.alert.model.FireStationMapping;
import com.safetynet.alert.model.MedicalRecord;
import com.safetynet.alert.model.Person;
import com.safetynet.alert.service.IFireStationService;
import com.safetynet.alert.service.IMedicalRecordService;
import com.safetynet.alert.service.IPersonService;
import com.safetynet.alert.service.PersonServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
	
	@Mock
	private IPersonDAO personDao;
	
	@Mock
	private IMedicalRecordService medicalRecordService;
	
	@Mock
	private IFireStationDAO fireStationDao;
	
	private IPersonService personService;
	
	private static TestData testData = new TestData();
	
	@BeforeEach
	public void setUpPerTest() {
		personService = new PersonServiceImpl(personDao, medicalRecordService, fireStationDao);
	}
	
	@Test
	public void testGetListOfMailByCity_shouldReturnListOfAllCulverMails() {
		when(personDao.findByCity(anyString())).thenReturn(testData.getPersons());
		
		List<String> mails = personService.getPersonEmailByCity(anyString());
		
		verify(personDao, Mockito.times(1)).findByCity(anyString());
		assertEquals(15, mails.size());
	}
	
	@Test
	public void testGetChildByAddress_shouldReturnAtLeastOneObject() throws MedicalRecordNotFoundException {
		Person child = testData.getPersons().get(3);
		MedicalRecord childMedicalRecord = testData.getMedicalRecords().get(3);
		List<Person> persons = new ArrayList<Person>();
		persons.add(child);
		
		when(personDao.findByAddress(anyString())).thenReturn(persons);
		when(medicalRecordService.getMedicalRecordOf(child.getFirstName(), child.getLastName())).thenReturn(childMedicalRecord);
		when(medicalRecordService.getAgeOf(childMedicalRecord)).thenReturn(12);
		when(medicalRecordService.isChild(any(MedicalRecord.class))).thenReturn(true);
		
		
		List<ChildInfoDTO> childsInfo = personService.getChildByAddress("");
		
		verify(medicalRecordService, Mockito.times(1)).getAgeOf(any(MedicalRecord.class));
		verify(personDao, Mockito.times(1)).findByAddress(anyString());
		
		assertEquals(1, childsInfo.size());
	}
	
	@Test
	public void testGetAddressReport_shouldReturnAddressReportCorrectlyFilled() throws MedicalRecordNotFoundException {
		final String ADDRESS = "1509 Culver St";

		when(fireStationDao.findByAddress(anyString())).thenReturn(testData.getFireStationsCovering1509CulverSt());
		
		FireDTO addressReport = personService.getFireDTO(ADDRESS);
		
		assertEquals(3, addressReport.getStationNumber().get(0));
	}
	
	@Test
	public void testGetAddressReportPersonDTO_shouldReturnCorrectlyFilledDTO() throws MedicalRecordNotFoundException {
		final String ADDRESS = "1509 Culver St";
		List<Person> personsWithSameAddress = testData.getFamillyAt1509CulverSt();
		
		when(personDao.findByAddress(anyString())).thenReturn(personsWithSameAddress);
		when(medicalRecordService.getMedicalRecordOf(anyString(), anyString())).thenReturn(new MedicalRecord());
		when(medicalRecordService.getAgeOf(any(MedicalRecord.class))).thenReturn(20);
		
		List<PersonInfoDTO> personInfoList = personService.getPersonInfoListByAddress(ADDRESS);
		
		assertEquals(5, personInfoList.size());
		
	}

}
