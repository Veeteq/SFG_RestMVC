package com.wojnarowicz.sfg.gw.batch;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.wojnarowicz.sfg.gw.api.controller.ESBController;
import com.wojnarowicz.sfg.gw.api.model.kias.KiasDataDTO;
import com.wojnarowicz.sfg.gw.api.model.kias.KiasMatchPaymentAccountDTO;
import com.wojnarowicz.sfg.gw.api.model.kias.KiasRequestDTO;
import com.wojnarowicz.sfg.gw.api.model.kias.KiasRootDTO;
import com.wojnarowicz.sfg.gw.api.model.kias.ParamsDTO;
import com.wojnarowicz.sfg.gw.domain.KiasExpectedPayment;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KiasBatchWriter implements ItemWriter<KiasExpectedPayment> {

    DateTimeFormatter formatToKey = DateTimeFormatter.ofPattern("ddMMyyyyHHmm");
    DateTimeFormatter formatToDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    @Override
    public void write(List<? extends KiasExpectedPayment> payments) throws Exception {
        payments.forEach(payment -> {
            log.info(payment.getExpectedPaymentNum() + ", " +  payment.getBcPublicId());
           
            LocalDateTime currentDateTime = LocalDateTime.now();
            
            KiasMatchPaymentAccountDTO matchPaymentAccount = new KiasMatchPaymentAccountDTO();
            matchPaymentAccount.setExpPaymentId(payment.getBcPublicId());
            matchPaymentAccount.setTransactionDate(currentDateTime.format(formatToDate));
            matchPaymentAccount.setPaidAmount(payment.getAmount().toString());
            matchPaymentAccount.setPaidDocId("AAA");
            matchPaymentAccount.setPaidDocNum("101");
            matchPaymentAccount.setPaidDate(currentDateTime.format(formatToDate));
            matchPaymentAccount.setPaidAmountTotal(payment.getAmount().toString());
            matchPaymentAccount.setPayerFullName(payment.getPayerFullName());
            matchPaymentAccount.setInn(payment.getPayerInn());
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
            
            log.info(ESBController.asJsonString(root));
        });
        
        payments.stream().map(payment -> {
            System.out.println("Data Retreived for Expected Payments: " + payment.getBcPublicId());

            KiasRootDTO root = new KiasRootDTO();
            KiasDataDTO data = new KiasDataDTO();
            KiasRequestDTO request = new KiasRequestDTO();

            KiasMatchPaymentAccountDTO matchPaymentAccount = new KiasMatchPaymentAccountDTO();
            matchPaymentAccount.setPaidAmount(payment.getAmount().toString());

            ParamsDTO params = new ParamsDTO();
            params.setMatchPaymentAccount(matchPaymentAccount);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyyHHmm");

            String transactionId = LocalDateTime.now().format(formatter);        
            request.setTransactionId(transactionId);
            request.setParams(params);
            data.setRequest(request);

            root.setData(data);

            return root;
        });
    }
}
