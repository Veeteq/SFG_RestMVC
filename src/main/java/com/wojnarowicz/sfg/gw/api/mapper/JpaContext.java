package com.wojnarowicz.sfg.gw.api.mapper;

import javax.persistence.EntityManager;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import com.wojnarowicz.sfg.gw.domain.BCCoverage;
import com.wojnarowicz.sfg.gw.domain.BCExpectedPayment;

public class JpaContext {

    private final EntityManager entityManager;
    private BCExpectedPayment bcExpectedPayment;

    @Autowired
    public JpaContext(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    

    @BeforeMapping
    public void setEntity(@MappingTarget BCExpectedPayment bcExpectedPayment) {
       this.bcExpectedPayment = bcExpectedPayment;
       // you could do stuff with the EntityManager here
    }

    @AfterMapping
    public void establishRelation(@MappingTarget BCCoverage bcCoverage) {
        System.out.println("*****************************************************************************");
        bcCoverage.setExpectedPayment(bcExpectedPayment);
        // you could do stuff with the EntityManager here
    }
}
