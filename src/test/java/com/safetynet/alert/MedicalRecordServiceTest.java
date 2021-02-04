package com.safetynet.alert;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
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
	
	@BeforeEach
	public void setUpPerTest() {
		medicalRecordService = new MedicalRecordServiceImpl(medicalRecordDAO);
	}
	
	@Test
	public void getAgeTest_shouldReturnAgeInInteger() {
		LocalDate birthDateMr1 = LocalDate.parse("02/18/2012", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		Period expectedAgeMr1 = Period.between(birthDateMr1, LocalDate.now());

		int ageMr1 = medicalRecordService.getAgeOf(TestData.TestPerson.SAMANTHA.getMedicalRecord());
		
		assertEquals(expectedAgeMr1.getYears(), ageMr1);
		
	}
	
	@Test
	public void testIsChild() {
		MedicalRecord samanthaMedicalRecord = TestData.TestPerson.SAMANTHA.getMedicalRecord();
		
		assertTrue(medicalRecordService.isChild(samanthaMedicalRecord));
	}
	
	@Test
	public void testIsNotChild() {
		MedicalRecord johnMedicalRecord = TestData.TestPerson.JOHN.getMedicalRecord();
		
		assertFalse(medicalRecordService.isChild(johnMedicalRecord));
	}
}
