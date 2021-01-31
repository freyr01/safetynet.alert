package com.safetynet.alert;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.alert.dao.IFireStationDAO;
import com.safetynet.alert.dao.IPersonDAO;
import com.safetynet.alert.dto.FireStationCoverage;
import com.safetynet.alert.model.FireStationMapping;
import com.safetynet.alert.model.Person;
import com.safetynet.alert.service.FireStationServiceImpl;
import com.safetynet.alert.service.IFireStationService;
import com.safetynet.alert.service.IMedicalRecordService;

@ExtendWith(MockitoExtension.class)
public class FireStationServiceTest {
	
	
	static {

	}
	static List<FireStationMapping> fireStationMapping = new ArrayList<FireStationMapping>();
	static List<Person> persons = new ArrayList<Person>();
	static Person person1 = new Person("Eric", "Jori", "5 Ch de Vaugrenier", "Antibes", "06600" , "0401010101", "eric.jori@gmail.com");
	static Person person2 = new Person("Samantha", "Carson", "123 Gare st Lazare", "Paris", "92000", "0102030405", "samantha.carson@gmail.com");
	static Person person3 = new Person("John", "Watson", "5 Blv de la Mer", "Antibes", "06600", "0403232311", "ejohn.watson@gmail.com");
	static Person person4 = new Person("Geofrey", "Versat", "123 Gare st Lazare", "Paris", "92000", "0101010101", "geofrey.versat@gmail.com");
	static {
		persons.add(person1);
		persons.add(person2);
		persons.add(person4);
		
		FireStationMapping map1 = new FireStationMapping();
		map1.setAddress("5 Ch de Vaugrenier");
		map1.setStation(1);
		fireStationMapping.add(map1);

	}
	
	@Mock
	private IPersonDAO personDAO;
	
	@Mock
	private IMedicalRecordService medicalRecordService;
	
	@Mock
	private IFireStationDAO fireStationDAO;
	
	private IFireStationService fireStationService;
	
	@BeforeEach
	public void setUpPerTest() {
		fireStationService = new FireStationServiceImpl(fireStationDAO, personDAO, medicalRecordService);
	}
	
	@Test
	public void testStationJurisdictionLogic() {
		when(fireStationDAO.findByStationNumber(1)).thenReturn(fireStationMapping);
		when(personDAO.findAll()).thenReturn(persons);
		when(medicalRecordService.isChild(anyString(), anyString())).thenReturn(false);
		
		FireStationCoverage fireStationCoverage = fireStationService.getCoveredFolkOf(1);
		
		assertEquals(1, fireStationCoverage.getAdultCount());
		assertEquals(0, fireStationCoverage.getChildCount());
		assertEquals("Eric", fireStationCoverage.getPersons().get(0).getFirstName());
	}

}
