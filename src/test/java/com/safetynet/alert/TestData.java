package com.safetynet.alert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.safetynet.alert.dto.PersonInfoDTO;
import com.safetynet.alert.model.FireStationMapping;
import com.safetynet.alert.model.MedicalRecord;
import com.safetynet.alert.model.Person;

public class TestData {
	
	private List<FireStationMapping> fireStations;
	private List<MedicalRecord> medicalRecords;
	private List<Person> persons;
	
	public TestData() {
		fireStations = new ArrayList<FireStationMapping>();

		fireStations.add(new FireStationMapping("1509 Culver St", 3)); //0
        fireStations.add(new FireStationMapping("29 15th St", 2)); //1
        fireStations.add(new FireStationMapping("834 Binoc Ave", 3)); //2
        fireStations.add(new FireStationMapping("644 Gershwin Cir", 1)); //3
        fireStations.add(new FireStationMapping("748 Townings Dr", 3)); //4
        fireStations.add(new FireStationMapping("112 Steppes Pl", 3)); //5
        fireStations.add(new FireStationMapping("489 Manchester St", 4)); //6
        fireStations.add(new FireStationMapping("892 Downing Ct", 2)); //7
        fireStations.add(new FireStationMapping("908 73rd St", 1)); //8
        fireStations.add(new FireStationMapping("112 Steppes Pl", 4)); //9
        fireStations.add(new FireStationMapping("947 E. Rose Dr", 1)); //10
        fireStations.add(new FireStationMapping("748 Townings Dr", 3)); //11
        fireStations.add(new FireStationMapping("951 LoneTree Rd", 2)); //12
		
		medicalRecords = new ArrayList<MedicalRecord>();
		medicalRecords.add(new MedicalRecord("John", "Boyd", "03/06/1984", Arrays.asList("aznol:350mg", "hydrapermazol:100mg"), Arrays.asList("nillacilan") )); //0
		medicalRecords.add(new MedicalRecord("Jacob", "Boyd", "03/06/1989", Arrays.asList("pharmacol:5000mg", "terazine:10mg", "noznazol:250mg"), null )); //1
		medicalRecords.add(new MedicalRecord("Tenley", "Boyd", "02/18/2012", null, Arrays.asList("peanut") )); //2
		medicalRecords.add(new MedicalRecord("Roger", "Boyd", "09/06/2017", null, null )); //3
		medicalRecords.add(new MedicalRecord("Felicia", "Boyd","01/08/1986", Arrays.asList("tetracyclaz:650mg"), Arrays.asList("xilliathal") ));
		medicalRecords.add(new MedicalRecord("Jonanathan", "Marrack", "01/03/1989", null, null ));
		medicalRecords.add(new MedicalRecord("Tessa", "Carman", "02/18/2012", null, null ));
		medicalRecords.add(new MedicalRecord("Peter", "Duncan", "09/06/2000", null, Arrays.asList("shellfish") ));
		medicalRecords.add(new MedicalRecord("Foster", "Shepard", "01/08/1980", null, null ));
		medicalRecords.add(new MedicalRecord("Tony", "Cooper", "03/06/1994", Arrays.asList("hydrapermazol:300mg", "dodoxadin:30mg"), Arrays.asList("shellfish") ));
		medicalRecords.add(new MedicalRecord("Lily", "Cooper", "03/06/1994", null, null ));
		medicalRecords.add(new MedicalRecord("Sophia", "Zemicks", "03/06/1988", Arrays.asList("aznol:60mg", "hydrapermazol:900mg", "pharmacol:5000mg", "terazine:500mg"), Arrays.asList("peanut", "shellfish", "aznol") ));
		medicalRecords.add(new MedicalRecord("Warren", "Zemicks", "03/06/1985", null, null ));
		medicalRecords.add(new MedicalRecord("Zach", "Zemicks", "03/06/2017", null, null ));
		medicalRecords.add(new MedicalRecord("Reginold", "Walker", "08/30/1979", Arrays.asList("thradox:700mg"), Arrays.asList("illisoxian") ));
		medicalRecords.add(new MedicalRecord("Jamie", "Peters", "03/06/1982", null, null ));
		medicalRecords.add(new MedicalRecord("Ron", "Peters", "04/06/1965", null, null ));
		medicalRecords.add(new MedicalRecord("Allison", "Boyd", "03/15/1965", Arrays.asList("aznol:200mg"), Arrays.asList("nillacilan") ));
		medicalRecords.add(new MedicalRecord("Brian", "Stelzer", "12/06/1975", Arrays.asList("ibupurin:200mg", "hydrapermazol:400mg"), Arrays.asList("nillacilan") ));
		medicalRecords.add(new MedicalRecord("Shawna", "Stelzer", "07/08/1980", null, null ));
		medicalRecords.add(new MedicalRecord("Kendrik", "Stelzer", "03/06/2014", Arrays.asList("noxidian:100mg", "pharmacol:2500mg"), null ));
		medicalRecords.add(new MedicalRecord("Clive", "Ferguson", "03/06/1994", null, null ));
		medicalRecords.add(new MedicalRecord("Eric", "Cadigan", "08/06/1945", Arrays.asList("tradoxidine:400mg"), null));
		
		persons = new ArrayList<Person>();
		persons.add(new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com")); //0
        persons.add(new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6513", "drk@email.com")); //1
        persons.add(new Person("Tenley", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "tenz@email.com")); //2
        persons.add(new Person("Roger", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com")); //3
        persons.add(new Person("Felicia", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6544", "jaboyd@email.com")); //4
        persons.add(new Person("Jonanathan", "Marrack", "29 15th St", "Culver", "97451", "841-874-6513", "drk@email.com")); //5
        persons.add(new Person("Tessa", "Carman", "834 Binoc Ave", "Culver", "97451", "841-874-6512", "tenz@email.com")); //6
        persons.add(new Person("Peter", "Duncan", "644 Gershwin Cir", "Culver", "97451", "841-874-6512", "jaboyd@email.com")); //7
        persons.add(new Person("Foster", "Shepard", "748 Townings Dr", "Culver", "97451", "841-874-6544", "jaboyd@email.com")); //8
        persons.add(new Person("Tony", "Cooper", "112 Steppes Pl", "Culver", "97451", "841-874-6874", "tcoop@ymail.com")); //9
        persons.add(new Person("Lily", "Cooper", "489 Manchester St", "Culver", "97451", "841-874-9845", "lily@email.com")); //10
        persons.add(new Person("Sophia", "Zemicks", "892 Downing Ct", "Culver", "97451", "841-874-7878", "soph@email.com")); //11
        persons.add(new Person("Warren", "Zemicks", "892 Downing Ct", "Culver", "97451", "841-874-7512", "ward@email.com")); //12
        persons.add(new Person("Zach", "Zemicks", "892 Downing Ct", "Culver", "97451", "841-874-7512", "zarc@email.com")); //13
        persons.add(new Person("Reginold", "Walker", "908 73rd St", "Culver", "97451", "841-874-8547", "reg@email.com")); //14
        persons.add(new Person("Jamie", "Peters", "908 73rd St", "Culver", "97451", "841-874-7462", "jpeter@email.com")); //15
        persons.add(new Person("Ron", "Peters", "112 Steppes Pl", "Culver", "97451", "841-874-8888", "jpeter@email.com")); //16
        persons.add(new Person("Allison", "Boyd", "112 Steppes Pl", "Culver", "97451", "841-874-9888", "aly@imail.com")); //17
        persons.add(new Person("Brian", "Stelzer", "947 E. Rose Dr", "Culver", "97451", "841-874-7784", "bstel@email.com")); //18
        persons.add(new Person("Shawna", "Stelzer", "947 E. Rose Dr", "Culver", "97451", "841-874-7784", "ssanw@email.com")); //19
        persons.add(new Person("Kendrik", "Stelzer", "947 E. Rose Dr", "Culver", "97451", "841-874-7784", "bstel@email.com")); //20
        persons.add(new Person("Clive", "Ferguson", "748 Townings Dr", "Culver", "97451", "841-874-6741", "clivfd@ymail.com")); //21
        persons.add(new Person("Eric", "Cadigan", "951 LoneTree Rd", "Culver", "97451", "841-874-7458", "gramps@email.com")); //22
	}
	
	public List<FireStationMapping> getFireStationMappingForStation2(){
		List<FireStationMapping> station2List = new ArrayList<FireStationMapping>();
		station2List.add(fireStations.get(1)); //29 15th St
		station2List.add(fireStations.get(7)); //892 Downing Ct
		station2List.add(fireStations.get(12)); //951 LoneTree Rd
		
		return station2List;
	}
	public List<PersonInfoDTO> getPersonInfoForAddress2915thSt(){
		List<PersonInfoDTO> personsByAddress = new ArrayList<PersonInfoDTO>();
		personsByAddress.add(toPersonInfoDTO(persons.get(5)));
		return personsByAddress;
	}
	public List<PersonInfoDTO> getPersonInfoForAddress892DowningCt(){
		List<PersonInfoDTO> personsByAddress = new ArrayList<PersonInfoDTO>();
		personsByAddress.add(toPersonInfoDTO(persons.get(11)));
		personsByAddress.add(toPersonInfoDTO(persons.get(12)));
		personsByAddress.add(toPersonInfoDTO(persons.get(13)));
		return personsByAddress;
	}
	public List<PersonInfoDTO> getPersonInfoForAddress951LoneTreeRd(){
		List<PersonInfoDTO> personsByAddress = new ArrayList<PersonInfoDTO>();
		personsByAddress.add(toPersonInfoDTO(persons.get(22)));
		return personsByAddress;
	}
	public List<Person> getFamillyAt1509CulverSt(){
		List<Person> familly = new ArrayList<Person>();
		familly.add(persons.get(0));
		familly.add(persons.get(1));
		familly.add(persons.get(2));
		familly.add(persons.get(3));
		familly.add(persons.get(4));
		
		return familly;
	}

	public List<FireStationMapping> getFireStations() {
		return fireStations;
	}

	public List<MedicalRecord> getMedicalRecords() {
		return medicalRecords;
	}

	public List<Person> getPersons() {
		return persons;
	}
	
	private PersonInfoDTO toPersonInfoDTO(Person p) {
		PersonInfoDTO personInfo = new PersonInfoDTO();
		personInfo.setFirstName(p.getFirstName());
		personInfo.setLastName(p.getLastName());
		personInfo.setAge(0);
		personInfo.setEmail(p.getEmail());
		personInfo.setPhone(p.getPhone());
		personInfo.setAddress(p.getAddress());
		personInfo.setMedications(null);
		personInfo.setAllergies(null);
		
		return personInfo;
	}
	
	public List<FireStationMapping> getFireStationsCovering1509CulverSt(){
		List<FireStationMapping> fireStationMappingList = new ArrayList<FireStationMapping>();
		fireStationMappingList.add(fireStations.get(0));
		
		return fireStationMappingList;
	}
	

	
}
