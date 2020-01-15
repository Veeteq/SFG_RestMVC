package com.wojnarowicz.sfg.gw.api.mapper;

import org.springframework.stereotype.Component;

import com.wojnarowicz.sfg.gw.api.model.kias.KiasRequestDTO;
import com.wojnarowicz.sfg.gw.domain.KiasRequestData;

@Component
public class KiasRequestMapperImpl implements KiasRequestMapper {

    @Override
    public KiasRequestData requestDataDTOToRequestData(KiasRequestDTO requestData) {


        if ( requestData == null ) {
            return null;
        }

        KiasRequestData kiasRequestData = new KiasRequestData();

        return kiasRequestData;
    }
}
