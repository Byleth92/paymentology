package com.paymentology.transactions.matcher.utils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import com.paymentology.transactions.matcher.constants.DatesAndTime;
import com.paymentology.transactions.matcher.constants.Strings;

public class CompareFieldTypes {
	
	public static boolean areTwoStringsEqual(String str1, String str2) {
		
		Boolean comparisonDoneWhenNotNull = areNullElementsEqual(str1, str2);
		if(Objects.nonNull(comparisonDoneWhenNotNull))
			return comparisonDoneWhenNotNull;
		
		return str1.replaceAll(Strings.SPACE, Strings.EMPTY)
				   .compareToIgnoreCase(str2.replaceAll(Strings.SPACE, Strings.EMPTY)) == 0 ? true : false;
	}
	
	public static boolean areStringAndBigDecimalStrEqual(String str1, BigDecimal bd1) {
		
		Boolean comparisonDoneWhenNotNull = areNullElementsEqual(str1, bd1);
		if(Objects.nonNull(comparisonDoneWhenNotNull))
			return comparisonDoneWhenNotNull;
		
		return bd1.compareTo(new BigDecimal(str1)) == 0 ? true : false;
	}
	
	public static boolean areStringAndLocalDateTimeEqual(String str1, LocalDateTime ldt1) {
		Boolean comparisonDoneWhenNotNull = areNullElementsEqual(str1, ldt1);
		if(Objects.nonNull(comparisonDoneWhenNotNull))
			return comparisonDoneWhenNotNull;
		
		return ldt1.equals(LocalDateTime.parse(str1, DatesAndTime.FORMATTER_DATE_TIME_ddMMyyyy_HHmmss));
	}
	
	private static Boolean areNullElementsEqual(Object obj1, Object obj2) {
		
		if(Objects.isNull(obj1) && Objects.isNull(obj2))
			return true;
		else if(Objects.isNull(obj1) && Objects.nonNull(obj2) || Objects.nonNull(obj1) && Objects.isNull(obj2))
			return false;
		
		return null;
	}
}
