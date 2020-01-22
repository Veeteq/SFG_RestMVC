package com.wojnarowicz.sfg.gw.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.wojnarowicz.sfg.gw.api.model.kias.KiasDataDTO;
import com.wojnarowicz.sfg.gw.api.model.kias.KiasMatchPaymentAccountDTO;
import com.wojnarowicz.sfg.gw.api.model.kias.KiasRequestDTO;
import com.wojnarowicz.sfg.gw.api.model.kias.KiasRootDTO;
import com.wojnarowicz.sfg.gw.api.model.kias.ParamsDTO;
import com.wojnarowicz.sfg.gw.domain.KiasExpectedPayment;

public class BCDataApiMapper {

    private DateTimeFormatter formatToKey = DateTimeFormatter.ofPattern("ddMMyyyyHHmm");
    private DateTimeFormatter formatToDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    
    public KiasRootDTO mapMatchPaymentRequest(KiasExpectedPayment kiasExpectedPayment) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        
        KiasMatchPaymentAccountDTO matchPaymentAccount = new KiasMatchPaymentAccountDTO();
        matchPaymentAccount.setExpPaymentId(kiasExpectedPayment.getPublicId());
        matchPaymentAccount.setTransactionDate(currentDateTime.format(formatToDate));
        matchPaymentAccount.setPaidAmount(kiasExpectedPayment.getAmount().toString());
        matchPaymentAccount.setPaidDocId("AAA");
        matchPaymentAccount.setPaidDocNum("101");
        matchPaymentAccount.setPaidDate(currentDateTime.format(formatToDate));
        matchPaymentAccount.setPaidAmountTotal(kiasExpectedPayment.getAmount().toString());
        matchPaymentAccount.setPayerFullName(kiasExpectedPayment.getPayerFullName());
        matchPaymentAccount.setInn(kiasExpectedPayment.getPayerInn());
        matchPaymentAccount.setComment("Перевод");
        matchPaymentAccount.setCurrency("RUB");
        matchPaymentAccount.setOrigPaidDocId("AAA");
        matchPaymentAccount.setOrigPaidDocNum("101");
        matchPaymentAccount.setOrigPaidDate(currentDateTime.format(formatToDate));
        
        ParamsDTO params = new ParamsDTO();
        params.setMatchPaymentAccount(matchPaymentAccount);

        KiasRequestDTO request = new KiasRequestDTO();            
        request.setTransactionId(currentDateTime.format(formatToKey));
        request.setParams(params);
        
        KiasDataDTO data = new KiasDataDTO();            
        data.setRequest(request);

        KiasRootDTO root = new KiasRootDTO();
        root.setData(data);
        
        return root;
    }
}
