package com.wojnarowicz.sfg.gw.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

	private static final String DATE_PATTERN = "yyyy-MM-dd";
	private static final String DATE_PATTERN_LONG = "yyyy-MM-dd HH:mm:ss";
	
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);
	private static final DateTimeFormatter DATE_FORMATTER_LONG = DateTimeFormatter.ofPattern(DATE_PATTERN_LONG);
	
	public static LocalDateTime parse(String dateString) {
		return LocalDate.parse(dateString, DATE_FORMATTER).atStartOfDay();
	}

	public static String format(LocalDateTime localDate) {
		if (localDate == null)
			return null;
		return DATE_FORMATTER.format(localDate);
	}
	
	public static String formatLong(LocalDateTime localDate) {
        if (localDate == null)
            return null;
        return DATE_FORMATTER_LONG.format(localDate);
    }
}
