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
public class SapRootDTO {

    @JsonProperty("AgentInfo")
    private List<BCAgentInfoDTO> agents = null;
    
    @JsonProperty("Coverages")
    private List<BCCoverageDTO> coverages = null;
    
    @JsonProperty("ExpectedPaymentDate")
    private String expectedPaymentDate;
    
    @JsonProperty("ExpectedPaymentPublicID")
    private String publicId;
    
    @JsonProperty("PaymentAmount")
    private BigDecimal paymentAmount;
    
    @JsonProperty("PaymentCurrency")
    private String paymentCurrency;
    
    @JsonProperty("PaymentDateOnAccountRGS")
    private String paymentDateOnAccountRGS;
    
    @JsonProperty("LastPaymentDate")
    private String lastPaymentDate;
    
    @JsonProperty("YetPayInsurancePremium")
    private BigDecimal yetPayInsurancePremium;
    
    @JsonProperty("PaymentStatus")
    private Integer paymentStatus;
    
    @JsonProperty("TypeOfOperation")
    private String typeOfOperation;
    
    @JsonProperty("ProductCode")
    private String productCode;
    
    @JsonProperty("SubProductCode")
    private String subProductCode;
    
    @JsonProperty("DefaultBaseState")
    private Integer defaultBaseState;
    
    @JsonProperty("PCPublicID")
    private String policyId;
    
    @JsonProperty("StatementDate")
    private String statementDate;
    
    @JsonProperty("StatementNumber")
    private String statementNumber;
    
    @JsonProperty("TermNumber")
    private Integer termNumber;
}

