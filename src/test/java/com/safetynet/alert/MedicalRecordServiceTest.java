package com.safetynet.alert;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.alert.dao.MedicalRecordDAOImpl;
import com.safetynet.alert.model.MedicalRecord;
import com.safetynet.alert.service.MedicalRecordServiceImpl;


@SpringBootTest
public class MedicalRecordServiceTest {
	
	static List<MedicalRecord> medicalRecords = new ArrayList<MedicalRecord>();
	static {
		MedicalRecord mr1 = new MedicalRecord();
		mr1.setFirstName("Samantha");
		mr1.setLastName("Carson");
		mr1.setBirthdate("02/18/2012");
		medicalRecords.add(mr1);
		MedicalRecord mr2 = new MedicalRecord();
		mr2.setFirstName("Billy");
		mr2.setLastName("Kid");
		mr2.setBirthdate("01/21/2011");
		medicalRecords.add(mr2);
	}
	
	@Mock
	MedicalRecordDAOImpl medicalRecordDAO;
	
	@InjectMocks
	MedicalRecordServiceImpl medicalRecordService;
	
	@Test
	public void getAgeTest_shouldReturnAgeInInteger() {
		when(medicalRecordDAO.findAll()).thenReturn(medicalRecords);
		LocalDate birthDateMr1 = LocalDate.parse("02/18/2012", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		LocalDate birthDateMr2 = LocalDate.parse("01/21/2011", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		Period expectedAgeMr1 = Period.between(birthDateMr1, LocalDate.now());
		Period expectedAgeMr2 = Period.between(birthDateMr2, LocalDate.now());

		int ageMr1 = medicalRecordService.getAgeOf("Samantha", "Carson");
		int ageMr2 = medicalRecordService.getAgeOf("Billy", "Kid");
		
		verify(medicalRecordDAO, Mockito.times(2)).findAll();
		assertEquals(expectedAgeMr1.getYears(), ageMr1);
		assertEquals(expectedAgeMr2.getYears(), ageMr2);
		
	}
}
