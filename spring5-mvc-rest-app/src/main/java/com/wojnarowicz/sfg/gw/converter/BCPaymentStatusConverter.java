package com.wojnarowicz.sfg.gw.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.wojnarowicz.sfg.gw.domain.BCPaymentStatus;

@Converter
public class BCPaymentStatusConverter implements AttributeConverter<BCPaymentStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(BCPaymentStatus paymentStatus) {        
        return paymentStatus.getId();
    }

    @Override
    public BCPaymentStatus convertToEntityAttribute(Integer id) {
        return BCPaymentStatus.getById(id);
    }

}
