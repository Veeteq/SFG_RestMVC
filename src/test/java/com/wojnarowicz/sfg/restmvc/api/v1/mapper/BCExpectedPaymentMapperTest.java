package com.wojnarowicz.sfg.restmvc.api.v1.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.wojnarowicz.sfg.gw.api.mapper.BCExpectedPaymentMapper;
import com.wojnarowicz.sfg.gw.api.mapper.JpaContext;
import com.wojnarowicz.sfg.gw.api.model.sap.SapRootDTO;
import com.wojnarowicz.sfg.gw.domain.BCExpectedPayment;
import com.wojnarowicz.sfg.gw.domain.PCCurrency;

class BCExpectedPaymentMapperTest {

    private final static String publicId = "id1";
    private final static String paymentCurrency = "rub";
    private BCExpectedPaymentMapper mapper = BCExpectedPaymentMapper.INSTANCE;
    
    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    void toBCExpectedPaymentTest() {
        SapRootDTO sapRootDTO = new SapRootDTO();
        sapRootDTO.setPublicId(publicId);
        sapRootDTO.setPaymentCurrency(paymentCurrency);
        
        // context
        JpaContext jpaCtx = new JpaContext();
        BCExpectedPayment bcExpectedPayment = mapper.toBCExpectedPayment(sapRootDTO, jpaCtx);
        
        assertEquals(publicId, bcExpectedPayment.getPublicId());
        assertEquals(PCCurrency.RUB, bcExpectedPayment.getPaymentCurrency());
    }

}
