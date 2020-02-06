package com.wojnarowicz.sfg.gw.api.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.wojnarowicz.sfg.gw.api.model.sap.BCCoverageDTO;
import com.wojnarowicz.sfg.gw.api.model.sap.SapRootDTO;
import com.wojnarowicz.sfg.gw.domain.BCCoverage;
import com.wojnarowicz.sfg.gw.domain.BCExpectedPayment;
import com.wojnarowicz.sfg.gw.domain.BCPaymentStatus;
import com.wojnarowicz.sfg.gw.domain.PCCurrency;

@Mapper(componentModel = "spring")
public interface BCExpectedPaymentMapper {

    BCExpectedPaymentMapper INSTANCE = Mappers.getMapper(BCExpectedPaymentMapper.class);

    @Mapping(source = "publicId", target = "id")
    BCExpectedPayment toBCExpectedPayment(SapRootDTO sapRootDTO, @Context JpaContext ctx);
    
    @Mapping(target = "expectedPayment", ignore = true)
    @Mapping(source = "chargeItemId",    target = "id")
    BCCoverage toBCoverage(BCCoverageDTO bcCoverageDTO, @Context JpaContext ctx);
    
    default BCPaymentStatus map(int id) {
        return BCPaymentStatus.getById(id);
    }
    
    default PCCurrency map(String s) {
        return PCCurrency.valueOf(s.toUpperCase());
    }
}
