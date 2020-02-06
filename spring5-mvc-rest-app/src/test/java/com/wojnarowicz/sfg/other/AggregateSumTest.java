package com.wojnarowicz.sfg.other;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AggregateSumTest {

    List<Something> somethings = new ArrayList<>();
    
    private class Something {
        private String id;
        private String subId;
        private int value;
        
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
//        public String getSubId() {
//            return subId;
//        }
        public void setSubId(String subId) {
            this.subId = subId;
        }
//        public int getValue() {
//            return value;
//        }
        
        public void setValue(int value) {
            this.value = value;
        }
    }
    
    private class Tuple {
        String subId;
        int value;
    }
    
    @BeforeEach
    void setUp() throws Exception {
        Something smth1 = new Something();
        smth1.setId("id1");
        smth1.setSubId("sub1");
        smth1.setValue(10);
        somethings.add(smth1);
        
        Something smth2 = new Something();
        smth2.id = "id1";
        smth2.subId = "sub2";
        smth2.value = 10;
        somethings.add(smth2);
        
        Something smth3 = new Something();
        smth3.id = "id1";
        smth3.subId = "sub3";
        smth3.value = 10;
        somethings.add(smth3);
        
        Something smth4 = new Something();
        smth4.id = "id2";
        smth4.subId = "sub1";
        smth4.value = 10;
        somethings.add(smth4);
        
        Something smth5 = new Something();
        smth5.id = "id2";
        smth5.subId = "sub2";
        smth5.value = 10;
        somethings.add(smth5);
        
        Something smth6 = new Something();
        smth6.id = "id3";
        smth6.subId = "sub1";
        smth6.value = 10;
        somethings.add(smth6);
        
    }

    @Test
    void test() {
        Map<String, List<Something>> totalSomathings = somethings.stream()
                .collect(Collectors.groupingBy(Something::getId, Collectors.toList()));
        assertEquals(3, totalSomathings.size());
    }

}
