package com.wojnarowicz.sfg.gw.api.model.kias;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"EXPPAYMENTNUM",
"EXPPAYMENTDATE",
"CKKCODE",
"CURRENCY",
"FORMID",
"METHODPAYID",
"COMMENT",
"AMOUNTCURR",
"AMOUNTRUB",
"PAYERTYPE",
"PAYERJUR",
"PAYERINN",
"PAYERFULLNAME",
"LNR",
"CONTRACTS",
"CONFIRMATIONS",
"AGRID",
"PAYERKPP"
})
public class ParamsDTO {

    @JsonProperty("EXPPAYMENTNUM")
    public String expectedPaymentNum;

    @JsonProperty("EXPPAYMENTDATE")
    public String expectedPaymentDate;
    
    @JsonProperty("CKKCODE")
    public String skkCode;
    
    @JsonProperty("CURRENCY")
    public String currency;
    
    @JsonProperty("FORMID")
    public String formId;
    
    @JsonProperty("METHODPAYID")
    public String methodPayId;
    
    @JsonProperty("COMMENT")
    public String comment;
    
    @JsonProperty("AMOUNTCURR")
    public String amount;
    
    @JsonProperty("AMOUNTRUB")
    public String amountRub;
    
    @JsonProperty("PAYERTYPE")
    public String payerType;
    
    @JsonProperty("PAYERJUR")
    public String payerJur;
    
    @JsonProperty("PAYERINN")
    public String payerInn;
    
    @JsonProperty("PAYERFULLNAME")
    public String payerFullName;
    
    @JsonProperty("LNR")
    public String lnr;
    
    @JsonProperty("CONTRACTS")
    public List<ContractDTO> contracts = null;
    
    @JsonProperty("CONFIRMATIONS")
    public List<PaymentConfirmationDTO> confirmations = null;
    
    @JsonProperty("AGRID")
    public Object agrId;
    
    @JsonProperty("PAYERKPP")
    public Object payerKpp;
}
