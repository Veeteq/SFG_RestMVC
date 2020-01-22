package com.wojnarowicz.sfg.gw.mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.wojnarowicz.sfg.gw.api.model.esb.PCPolicyDTO;
import com.wojnarowicz.sfg.gw.domain.PCPolicy;

@Mapper(componentModel = "spring", 
        imports = {LocalDate.class, LocalDateTime.class, DateTimeFormatter.class})
public interface PCPolicyMapper {

    PCPolicyMapper INSTANCE = Mappers.getMapper(PCPolicyMapper.class);
    
    @Mapping(source = "policyPublicId", target = "publicId")
    @Mapping(target = "startDate",          expression = "java(LocalDateTime.parse(policyDTO.startDate, DateTimeFormatter.ISO_ZONED_DATE_TIME))")
    @Mapping(target = "endDate",            expression = "java(LocalDateTime.parse(policyDTO.endDate, DateTimeFormatter.ISO_ZONED_DATE_TIME))")
    @Mapping(target = "insuredPremiumDate", expression = "java(LocalDate.parse(policyDTO.insuredPremiumDate, DateTimeFormatter.ofPattern(\"yyyy-MM-dd\")))")
    @Mapping(target = "issueDate",          expression = "java(LocalDateTime.parse(policyDTO.issueDate, DateTimeFormatter.ISO_ZONED_DATE_TIME))")
    @Mapping(target = "liabilityStartDate", expression = "java(LocalDateTime.parse(policyDTO.liabilityStartDate, DateTimeFormatter.ISO_ZONED_DATE_TIME))")
    @Mapping(target = "liabilityEndDate",   expression = "java(LocalDateTime.parse(policyDTO.liabilityEndDate, DateTimeFormatter.ISO_ZONED_DATE_TIME))")
    PCPolicy pcPolicyDTOToPCPolicy(PCPolicyDTO policyDTO);
}
