package com.wojnarowicz.sfg.other;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DateConverterTest {

    private final static String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSX";
    
    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    void test() {
        Instant localDate = Instant.now();
        System.out.println(localDate);
        
        DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
        
        System.out.println(formatter.format(localDate));
        System.out.println(UUID.randomUUID().toString());
    }
}
