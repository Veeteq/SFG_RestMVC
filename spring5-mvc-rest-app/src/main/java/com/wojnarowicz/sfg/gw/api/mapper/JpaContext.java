package com.wojnarowicz.sfg.gw.api.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;

import com.wojnarowicz.sfg.gw.domain.BCCoverage;
import com.wojnarowicz.sfg.gw.domain.BCExpectedPayment;

public class JpaContext {

    private BCExpectedPayment bcExpectedPayment;

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
