package com.wojnarowicz.sfg.gw.mapper;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import com.wojnarowicz.sfg.gw.api.model.kias.KiasDataDTO;
import com.wojnarowicz.sfg.gw.api.model.kias.KiasMatchPaymentAccountDTO;
import com.wojnarowicz.sfg.gw.api.model.kias.KiasRequestDTO;
import com.wojnarowicz.sfg.gw.api.model.kias.KiasRootDTO;
import com.wojnarowicz.sfg.gw.api.model.kias.ParamsDTO;
import com.wojnarowicz.sfg.gw.api.model.sap.ActOfPerformanceCommissionDTO;
import com.wojnarowicz.sfg.gw.api.model.sap.ActOfPerformanceDTO;
import com.wojnarowicz.sfg.gw.domain.BCCoverage;
import com.wojnarowicz.sfg.gw.domain.BCExpectedPayment;
import com.wojnarowicz.sfg.gw.domain.KiasExpectedPayment;

public class BCDataApiMapper {

    private static final BigDecimal commissionPrcnt = new BigDecimal("0.1");
    
    private DateTimeFormatter formatToKey = DateTimeFormatter.ofPattern("ddMMyyyyHHmm");
    private DateTimeFormatter formatToDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    NumberFormat numberFormat;
    
    
    
    public BCDataApiMapper() {
        numberFormat = NumberFormat.getInstance(Locale.US);
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setGroupingUsed(false);
    }


    public KiasRootDTO mapMatchPaymentRequest(KiasExpectedPayment kiasExpectedPayment) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        
        KiasMatchPaymentAccountDTO matchPaymentAccount = new KiasMatchPaymentAccountDTO();
        matchPaymentAccount.setExpPaymentId(kiasExpectedPayment.getPublicId());
        matchPaymentAccount.setTransactionDate(currentDateTime.format(formatToDate));
        matchPaymentAccount.setPaidAmount(numberFormat.format(kiasExpectedPayment.getAmount().multiply(BigDecimal.ONE.subtract(commissionPrcnt))));
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


    public ActOfPerformanceDTO mapActOfPerformance(List<? extends BCExpectedPayment> payments) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        
        ActOfPerformanceDTO request = new ActOfPerformanceDTO();
        
        String actOfPerformanceId = currentDateTime.format(formatToKey);
        
        request.setId(actOfPerformanceId);
        request.setActDate(currentDateTime.format(formatToDate));
        request.setRefactNumber(actOfPerformanceId);
        request.setAgrId("1588426");
        request.setAgentId("1588426");
        request.setRetentionKv(1);
        
        List<ActOfPerformanceCommissionDTO> commissions = payments.stream()
                .map(BCExpectedPayment::getCoverages)
                .flatMap(List::stream)
                .map(coverage -> mapCoverageToCommissionDTO(coverage))
                .collect(Collectors.toList());
        
        request.setCommissions(commissions);
        
        return request;
    }
    
    private ActOfPerformanceCommissionDTO mapCoverageToCommissionDTO(BCCoverage bcCoverage) {
        ActOfPerformanceCommissionDTO commissionDTO = new ActOfPerformanceCommissionDTO();
        commissionDTO.setPolicyId(bcCoverage.getExpectedPayment().getPolicyId());
        commissionDTO.setPaymentId(bcCoverage.getExpectedPayment().getPublicId());
        commissionDTO.setChargeItemId(bcCoverage.getChargeItemId());
        commissionDTO.setReportSystemCode("Code");
        commissionDTO.setCommissionAmt(numberFormat.format(bcCoverage.getChargeAmount().multiply(commissionPrcnt)));
        commissionDTO.setCommissionCur(bcCoverage.getChargeCurrency().name());
        commissionDTO.setComissionTypeSap("SapType");
        return commissionDTO;
    }
}
