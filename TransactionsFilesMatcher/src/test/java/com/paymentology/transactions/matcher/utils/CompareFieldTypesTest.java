package com.paymentology.transactions.matcher.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.paymentology.transactions.matcher.constants.DatesAndTime;

@ExtendWith(MockitoExtension.class)
class CompareFieldTypesTest {

	@InjectMocks private CompareFieldTypes compareFieldTypes;
	
	@Test
	void test() {
		assertEquals(true, CompareFieldTypes.areTwoStringsEqual("string1", "string1"));
		assertEquals(false, CompareFieldTypes.areTwoStringsEqual("string1", null));
		assertEquals(false, CompareFieldTypes.areTwoStringsEqual(null, "string1"));
		assertEquals(false, CompareFieldTypes.areTwoStringsEqual("string1s", "string1"));
		assertEquals(true, CompareFieldTypes.areTwoStringsEqual(null, null));
	}
	
	@Test
	void test2() {
		assertEquals(true, CompareFieldTypes.areStringAndBigDecimalStrEqual("10", BigDecimal.TEN));
		assertEquals(false, CompareFieldTypes.areStringAndBigDecimalStrEqual("10", null));
		assertEquals(false, CompareFieldTypes.areStringAndBigDecimalStrEqual(null, BigDecimal.TEN));
		assertEquals(false, CompareFieldTypes.areStringAndBigDecimalStrEqual("10", BigDecimal.ONE));
		assertEquals(true, CompareFieldTypes.areStringAndBigDecimalStrEqual(null, null));
	}
	
	@Test
	void test3() {
		LocalDateTime data = LocalDateTime.parse("2021-10-01 10:00:00", DatesAndTime.FORMATTER_DATE_TIME_ddMMyyyy_HHmmss);
		
		assertEquals(true, CompareFieldTypes.areStringAndLocalDateTimeEqual("2021-10-01 10:00:00", data));
		assertEquals(false, CompareFieldTypes.areStringAndLocalDateTimeEqual("2021-10-01 10:00:01", data));
		assertEquals(false, CompareFieldTypes.areStringAndLocalDateTimeEqual(null, data));
		assertEquals(false, CompareFieldTypes.areStringAndLocalDateTimeEqual("2021-10-02 10:00:00", data));
		assertEquals(true, CompareFieldTypes.areStringAndLocalDateTimeEqual(null, null));
	}
}
