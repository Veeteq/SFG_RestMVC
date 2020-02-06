package com.wojnarowicz.sfg.gw.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.wojnarowicz.sfg.gw.api.model.kias.ParamsDTO;
import com.wojnarowicz.sfg.gw.domain.KiasExpectedPayment;

@Mapper
public interface KiasMapper {

    KiasMapper INSTANCE = Mappers.getMapper(KiasMapper.class);
    
    KiasExpectedPayment kiasParamsDTOToKias(ParamsDTO paramsDTO);
}
