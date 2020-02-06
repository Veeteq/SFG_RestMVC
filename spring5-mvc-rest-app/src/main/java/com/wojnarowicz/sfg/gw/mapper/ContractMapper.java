package com.wojnarowicz.sfg.gw.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.wojnarowicz.sfg.gw.api.model.bso.ContractDTO;
import com.wojnarowicz.sfg.gw.domain.Contract;

@Mapper
public interface ContractMapper {

    ContractMapper INSTANCE = Mappers.getMapper(ContractMapper.class);
    
    Contract contractDTOToContract(ContractDTO contractDTO);
}
