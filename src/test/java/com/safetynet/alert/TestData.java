package com.safetynet.alert;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import com.safetynet.alert.dto.PersonInfoDTO;
import com.safetynet.alert.model.FireStationMapping;
import com.safetynet.alert.model.MedicalRecord;
import com.safetynet.alert.model.Person;

public class TestData {
	public enum TestPerson {
		
		ERIC(new Person("Eric", "Jori", "5 Ch de Vaugrenier", "Antibes", "06600" , "0401010101", "eric.jori@gmail.com"),
				new MedicalRecord("Eric", "Jori", "02/18/1934", Arrays.asList("tetracyclaz:650mg", "terazine:10mg"), null)),
		SAMANTHA(new Person("Samantha", "Carson", "123 Gare st Lazare", "Paris", "92000", "0102030405", "samantha.carson@gmail.com"),
				new MedicalRecord("Samantha", "Carson", "02/18/2012", null, null)),
		JOHN( new Person("John", "Watson", "5 Blv de la Mer", "Antibes", "06600", "0403232311", "ejohn.watson@gmail.com"),
				new MedicalRecord("John", "Watson", "01/21/2002", null, null)),
		ALEX(new Person("Alex", "Versat", "123 Gare st Lazare", "Paris", "92000", "0101010101", "geofrey.versat@gmail.com"),
				new MedicalRecord("Alex", "Versat", "01/21/1999", null, null)),
		BILLY(new Person("Billy", "Kid", "5 Ch de Vaugrenier", "Antibes", "06600" , "0401010101", "billy.kid@gmail.com"),
				new MedicalRecord("Billy", "Kid", "01/21/2011", null, null));
		
		private Person person;
		private MedicalRecord medicalRecord;
		TestPerson(Person p_person, MedicalRecord p_medicalRecord){
			person = p_person;
			medicalRecord = p_medicalRecord;
		}
		
		public Person getPerson() {
			return person;
		}
		
		public MedicalRecord getMedicalRecord() {
			return medicalRecord;
		}
		
		public static List<Person> getPersonBySameAddress(){
			List<Person> personsBySameAddress = new ArrayList<Person>();
			personsBySameAddress.add(ERIC.getPerson());
			personsBySameAddress.add(BILLY.getPerson());
			
			return personsBySameAddress;
		}
		
		public static List<Person> getBySameCity() {
			List<Person> personsBySameCity = new ArrayList<Person>();
			personsBySameCity.add(ERIC.getPerson());
			personsBySameCity.add(JOHN.getPerson());
			personsBySameCity.add(BILLY.getPerson());
			
			return personsBySameCity;
		}
	}
	
	private static FireStationMapping fireStationMapping1 = new FireStationMapping("123 Gare st Lazare", 1);
	private static FireStationMapping fireStationMapping2 = new FireStationMapping("5 Ch de Vaugrenier", 2);
	
	public static FireStationMapping getTestFireStationMapping1() {
		return fireStationMapping1;
	}
	
	public static List<FireStationMapping> getTestFireStationMappingList(){
		List<FireStationMapping> fireStationMappingList = new ArrayList<FireStationMapping>();
		fireStationMappingList.add(fireStationMapping1);
		fireStationMappingList.add(fireStationMapping2);
		return fireStationMappingList;
	}
	
	public static List<PersonInfoDTO> getPersonInfoDTOByAddressList() {
		List<PersonInfoDTO> list = new ArrayList<PersonInfoDTO>();
		
		PersonInfoDTO ericDTO = new PersonInfoDTO();
		Person eric = TestPerson.ERIC.getPerson();
		
		ericDTO.setFirstName(eric.getFirstName());
		ericDTO.setLastName(eric.getLastName());
		ericDTO.setAge(20);
		ericDTO.setPhone(eric.getPhone());
		ericDTO.setMedications(TestPerson.ERIC.getMedicalRecord().getMedications());
		ericDTO.setAllergies(TestPerson.ERIC.getMedicalRecord().getMedications());
		
		Person alex = TestPerson.ALEX.getPerson();
		PersonInfoDTO alexDTO = new PersonInfoDTO();
		
		alexDTO.setFirstName(alex.getFirstName());
		alexDTO.setLastName(alex.getLastName());
		alexDTO.setAge(30);
		alexDTO.setPhone(alex.getPhone());
		alexDTO.setMedications(TestPerson.ALEX.getMedicalRecord().getMedications());
		alexDTO.setAllergies(TestPerson.ALEX.getMedicalRecord().getMedications());
		
		Person samantha = TestPerson.SAMANTHA.getPerson();
		PersonInfoDTO samanthaDTO = new PersonInfoDTO();
		
		samanthaDTO.setFirstName(samantha.getFirstName());
		samanthaDTO.setLastName(samantha.getLastName());
		samanthaDTO.setAge(30);
		samanthaDTO.setPhone(samantha.getPhone());
		samanthaDTO.setMedications(TestPerson.SAMANTHA.getMedicalRecord().getMedications());
		samanthaDTO.setAllergies(TestPerson.SAMANTHA.getMedicalRecord().getMedications());
		
		list.add(ericDTO);
		list.add(alexDTO);
		list.add(samanthaDTO);
		
		return list;
		
	}
}
