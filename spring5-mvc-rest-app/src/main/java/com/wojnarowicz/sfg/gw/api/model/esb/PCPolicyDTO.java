package com.wojnarowicz.sfg.gw.api.model.esb;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Account",
    "ActionTerritory",
    "AgentInfoList",
    "AgentStatement",
    "Branch",
    "Change",
    "CheckSumCorrect",
    "ClientSystem",
    "ContractOption",
    "ContractSign",
    "Currency",
    "DetentionTerritory",
    "DriverPermit",
    "EndDate",
    "Insured",
    "InsuredPremium",
    "InsuredPremiumDate",
    "IssueDate",
    "Issued",
    "LiabilityEndDate",
    "LiabilityStartDate",
    "Mvehicle",
    "Offering",
    "Owner",
    "PledgedProperty",
    "PolicyNumber",
    "PolicyNumberBSO",
    "PolicyPublicID",
    "PolicySeries",
    "PolicySeriesBSO",
    "PolicySuffix",
    "PolicySystemPeriodID",
    "Product",
    "Region",
    "SKKCode",
    "SalesChannelType",
    "StartDate",
    "Status",
    "SumInsured",
    "SumInsuredTotal",
    "SysOp"
})
public class PCPolicyDTO {

    @JsonProperty("Account")
    public String account;

    @JsonProperty("ActionTerritory")
    public String actionTerritory;

    //@JsonProperty("AgentInfoList")
    //public List<AgentInfoList> agentInfoList = null;

    //@JsonProperty("AgentStatement")
    //public AgentStatement agentStatement;

    @JsonProperty("Branch")
    public Integer branch;

    //@JsonProperty("Change")
    //public Change change;

    @JsonProperty("CheckSumCorrect")
    public String checkSumCorrect;

    @JsonProperty("ClientSystem")
    public String clientSystem;

    @JsonProperty("ContractOption")
    public String contractOption;

    @JsonProperty("ContractSign")
    public Boolean contractSign;

    @JsonProperty("Currency")
    public String currency;

    @JsonProperty("DetentionTerritory")
    public Integer detentionTerritory;

    @JsonProperty("DriverPermit")
    public String driverPermit;

    @JsonProperty("EndDate")
    public String endDate;

    @JsonProperty("Insured")
    public PCContactDTO insured;

    @JsonProperty("InsuredPremium")
    public String insuredPremium;

    @JsonProperty("InsuredPremiumDate")
    public String insuredPremiumDate;

    @JsonProperty("IssueDate")
    public String issueDate;

    @JsonProperty("Issued")
    public Boolean issued;

    @JsonProperty("LiabilityEndDate")
    public String liabilityEndDate;

    @JsonProperty("LiabilityStartDate")
    public String liabilityStartDate;

    @JsonProperty("Mvehicle")
    public PCMvehicleDTO mVehicle;

    @JsonProperty("Offering")
    public String offering;

    @JsonProperty("Owner")
    public PCContactDTO owner;

    @JsonProperty("PledgedProperty")
    public String pledgedProperty;

    @JsonProperty("PolicyNumber")
    public String policyNumber;

    @JsonProperty("PolicyNumberBSO")
    public String policyNumberBSO;

    @JsonProperty("PolicyPublicID")
    public String policyPublicId;

    @JsonProperty("PolicySeries")
    public String policySeries;

    @JsonProperty("PolicySeriesBSO")
    public String policySeriesBSO;

    @JsonProperty("PolicySuffix")
    public String policySuffix;

    @JsonProperty("PolicySystemPeriodID")
    public String policySystemPeriodId;

    @JsonProperty("Product")
    public String product;

    @JsonProperty("Region")
    public Integer region;

    @JsonProperty("SKKCode")
    public Integer sKKCode;

    @JsonProperty("SalesChannelType")
    public Integer salesChannelType;

    @JsonProperty("StartDate")
    public String startDate;

    @JsonProperty("Status")
    public String status;

    @JsonProperty("SumInsured")
    public String sumInsured;

    @JsonProperty("SumInsuredTotal")
    public String sumInsuredTotal;

    @JsonProperty("SysOp")
    public String sysOp;

}