package com.wojnarowicz.sfg.gw.it;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wojnarowicz.sfg.Spring5MvcRestApplication;
import com.wojnarowicz.sfg.gw.api.model.kias.KiasRootDTO;
import com.wojnarowicz.sfg.gw.domain.KiasExpectedPayment;
import com.wojnarowicz.sfg.gw.mapper.BCDataApiMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Spring5MvcRestApplication.class)
@TestPropertySource(locations="classpath:application.properties")
@ActiveProfiles("h2file")
class KiasBatchFlowTest {
    private static final ObjectMapper _mapper = initMapper();
    
    private static ObjectMapper initMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        mapper.setSerializationInclusion(Include.NON_EMPTY);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }
    
    @Autowired
    EntityManager entityManager;
    
    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    void testNoRecords() {
        List<KiasExpectedPayment> resultList = entityManager.createQuery("select p FROM KiasExpectedPayment p WHERE p.paymentStatus != 'MATCHED'", KiasExpectedPayment.class).getResultList();
        assertEquals(0, resultList.size());
    }
    
    @Test
    void testOneRecord() {
        List<KiasExpectedPayment> resultList = entityManager.createQuery("select p FROM KiasExpectedPayment p WHERE p.id = 'bc:1'", KiasExpectedPayment.class).getResultList();
        assertEquals(1, resultList.size());
    }
    
    @Test
    void testPayerType() {
        List<KiasExpectedPayment> resultList = entityManager.createQuery("select p FROM KiasExpectedPayment p WHERE p.paymentStatus = 'MATCHED'", KiasExpectedPayment.class).getResultList();
        KiasExpectedPayment kiasExpectedPayment = resultList.get(0);
        assertEquals("1", kiasExpectedPayment.getPayerType());
        
        BigDecimal paidAmountFactor = kiasExpectedPayment.getPayerType().equals("1") ? BigDecimal.ONE : BigDecimal.ZERO;
        assertEquals(1, paidAmountFactor.intValue());
    }
    
    @Test
    void testJsonMapping() throws JsonProcessingException {
        List<KiasExpectedPayment> resultList = entityManager.createQuery("select p FROM KiasExpectedPayment p WHERE p.paymentStatus = 'MATCHED'", KiasExpectedPayment.class).getResultList();
        KiasExpectedPayment kiasExpectedPayment = resultList.get(0);
        
        KiasRootDTO request = new BCDataApiMapper().mapMatchPaymentRequest(kiasExpectedPayment);
        String requestJson = _mapper.writerWithDefaultPrettyPrinter().writeValueAsString(request);
        assertNotNull(requestJson);
        
        System.out.println(requestJson);
    }
}
