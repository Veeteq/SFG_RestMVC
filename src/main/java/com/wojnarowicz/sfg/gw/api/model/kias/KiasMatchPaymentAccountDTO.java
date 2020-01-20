package com.wojnarowicz.sfg.gw.api.model.kias;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
"EXPPAYMENTID",
"TRANSACTIONDATE",
"PAYAMOUNT",
"PAYDOCID",
"PAYDOCNUM",
"PAYDATE",
"PAYAMOUNTTOTAL",
"PAYERFULLNAME",
"INN",
"COMMENT",
"CURRENCY",
"ORIGPAYDOCID",
"ORIGPAYDOCNUM",
"ORIGPAYDATE"
})
public class KiasMatchPaymentAccountDTO {

    @JsonProperty("EXPPAYMENTID")
    public String expPaymentId;
    
    @JsonProperty("TRANSACTIONDATE")
    public String transactionDate;
    
    @JsonProperty("PAYAMOUNT")
    public String paidAmount;
    
    @JsonProperty("PAYDOCID")
    public String paidDocId;
    
    @JsonProperty("PAYDOCNUM")
    public String paidDocNum;
    
    @JsonProperty("PAYDATE")
    public String paidDate;
    
    @JsonProperty("PAYAMOUNTTOTAL")
    public String paidAmountTotal;
    
    @JsonProperty("PAYERFULLNAME")
    public String payerFullName;
    
    @JsonProperty("INN")
    public String inn;
    
    @JsonProperty("COMMENT")
    public String comment;
    
    @JsonProperty("CURRENCY")
    public String currency;
    
    @JsonProperty("ORIGPAYDOCID")
    public String origPaidDocId;
    
    @JsonProperty("ORIGPAYDOCNUM")
    public String origPaidDocNum;
    
    @JsonProperty("ORIGPAYDATE")
    public String origPaidDate;
}
