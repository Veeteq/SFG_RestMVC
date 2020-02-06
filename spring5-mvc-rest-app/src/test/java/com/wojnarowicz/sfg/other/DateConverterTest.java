package com.wojnarowicz.sfg.other;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DateConverterTest {

    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    void test() {
        Instant localDate = Instant.now();
        System.out.println(localDate);
        
        DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
        
        System.out.println(formatter.format(localDate));
    }
    
    @Test
    void testParseLocalDateTime() {
        String var = "2019-09-24T23:00:00.000Z";
        
        LocalDateTime localDateTime = LocalDateTime.parse(var, DateTimeFormatter.ISO_ZONED_DATE_TIME);
        assertEquals(2019, localDateTime.getYear());
    }
    
    @Test
    void testParseLocalDate() {
        String var = "2019-09-25";
        LocalDate localDate = LocalDate.parse(var, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        assertEquals(2019, localDate.getYear());
        assertEquals(Month.SEPTEMBER, localDate.getMonth());
        assertEquals(25, localDate.getDayOfMonth());
    }
    
    @Test
    void testFormatZonedDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME; //ofPattern("yyyy-MM-dd HH:mm:ss.SSS z");
        String s = ZonedDateTime.now().format(formatter);
        System.out.println(s);
    }
}
