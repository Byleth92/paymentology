package com.paymentology.transactions.matcher.constants;

import java.time.format.DateTimeFormatter;

public interface DatesAndTime {
	public static final DateTimeFormatter FORMATTER_DATE_TIME_ddMMyyyy_HHmmss = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
}
