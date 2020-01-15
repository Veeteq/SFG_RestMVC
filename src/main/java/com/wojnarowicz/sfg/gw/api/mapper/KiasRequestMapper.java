package com.wojnarowicz.sfg.gw.api.mapper;

import com.wojnarowicz.sfg.gw.api.model.kias.KiasRequestDTO;
import com.wojnarowicz.sfg.gw.domain.KiasRequestData;

public interface KiasRequestMapper {

    KiasRequestData requestDataDTOToRequestData(KiasRequestDTO requestData);
}
