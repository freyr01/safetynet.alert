package com.safetynet.alert;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.alert.dao.MedicalRecordDAOImpl;
import com.safetynet.alert.model.MedicalRecord;
import com.safetynet.alert.service.IMedicalRecordService;
import com.safetynet.alert.service.MedicalRecordServiceImpl;

@ExtendWith(MockitoExtension.class)
public class MedicalRecordServiceTest {
	
	@Mock
	private static MedicalRecordDAOImpl medicalRecordDAO;
	
	private IMedicalRecordService medicalRecordService;
	
	private static TestData testData = new TestData();
	
	@BeforeEach
	public void setUpPerTest() {
		medicalRecordService = new MedicalRecordServiceImpl(medicalRecordDAO);
	}
	
	@Test
	public void getAgeTest_shouldReturnAgeInInteger() {
		MedicalRecord firstMedicalRecord = testData.getMedicalRecords().get(0);
		LocalDate birthDatePerson1 = LocalDate.parse(firstMedicalRecord.getBirthdate(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		Period expectedAge = Period.between(birthDatePerson1, LocalDate.now());

		int ageMr1 = medicalRecordService.getAgeOf(firstMedicalRecord);
		
		assertEquals(expectedAge.getYears(), ageMr1);
		
	}
	
	@Test
	public void testIsChild() {
		MedicalRecord childMedicalRecord = testData.getMedicalRecords().get(3);
		
		assertTrue(medicalRecordService.isChild(childMedicalRecord));
	}
	
	@Test
	public void testIsNotChild() {
		MedicalRecord adultMedicalRecord = testData.getMedicalRecords().get(1);
		
		assertFalse(medicalRecordService.isChild(adultMedicalRecord));
	}
}
