package com.wojnarowicz.sfg.gw.api.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.wojnarowicz.sfg.gw.api.model.sap.BCCoverageDTO;
import com.wojnarowicz.sfg.gw.api.model.sap.SapRootDTO;
import com.wojnarowicz.sfg.gw.domain.BCCoverage;
import com.wojnarowicz.sfg.gw.domain.BCExpectedPayment;
import com.wojnarowicz.sfg.gw.domain.PCCurrency;

@Mapper(componentModel = "spring")
public interface BCExpectedPaymentMapper {

    BCExpectedPaymentMapper INSTANCE = Mappers.getMapper(BCExpectedPaymentMapper.class);

    BCExpectedPayment toBCExpectedPayment(SapRootDTO sapRootDTO, @Context JpaContext ctx);
    
    @Mapping(target = "expectedPayment", ignore = true)
    BCCoverage toBCoverage(BCCoverageDTO bcCoverageDTO, @Context JpaContext ctx);
    
    default PCCurrency map(String s) {
        return PCCurrency.valueOf(s.toUpperCase());
    }
}
