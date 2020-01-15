package com.wojnarowicz.sfg.gw.api.model.sap;

import java.math.BigDecimal;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "AgentInfo",
    "Coverages",
    "ExpectedPaymentDate",
    "ExpectedPaymentPublicID",
    "PaymentAmount",
    "PaymentCurrency",
    "PaymentDateOnAccountRGS",
    "LastPaymentDate",
    "YetPayInsurancePremium",
    "PaymentStatus",
    "TypeOfOperation",
    "ProductCode",
    "SubProductCode",
    "DefaultBaseState",
    "PCPublicID",
    "StatementDate",
    "StatementNumber",
    "TermNumber"
})
public class SapRequestDTO {

    @JsonProperty("AgentInfo")
    public List<AgentInfoDTO> agentInfo = null;
    
    @JsonProperty("Coverages")
    public List<CoverageDTO> coverages = null;
    
    @JsonProperty("ExpectedPaymentDate")
    public String expectedPaymentDate;
    
    @JsonProperty("ExpectedPaymentPublicID")
    public String expectedPaymentPublicID;
    
    @JsonProperty("PaymentAmount")
    public BigDecimal paymentAmount;
    
    @JsonProperty("PaymentCurrency")
    public String paymentCurrency;
    
    @JsonProperty("PaymentDateOnAccountRGS")
    public String paymentDateOnAccountRGS;
    
    @JsonProperty("LastPaymentDate")
    public String lastPaymentDate;
    
    @JsonProperty("YetPayInsurancePremium")
    public BigDecimal yetPayInsurancePremium;
    
    @JsonProperty("PaymentStatus")
    public Integer paymentStatus;
    
    @JsonProperty("TypeOfOperation")
    public String typeOfOperation;
    
    @JsonProperty("ProductCode")
    public String productCode;
    
    @JsonProperty("SubProductCode")
    public String subProductCode;
    
    @JsonProperty("DefaultBaseState")
    public Integer defaultBaseState;
    
    @JsonProperty("PCPublicID")
    public String pCPublicID;
    
    @JsonProperty("StatementDate")
    public String statementDate;
    
    @JsonProperty("StatementNumber")
    public String statementNumber;
    
    @JsonProperty("TermNumber")
    public Integer termNumber;
}

