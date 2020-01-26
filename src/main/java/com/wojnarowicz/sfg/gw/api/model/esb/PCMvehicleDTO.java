package com.wojnarowicz.sfg.gw.api.model.esb;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "AnnualMileage",
    "AntiTheftSystem",
    "CarryingCapacity",
    "CostNewCur",
    "CostNew_amt",
    "CoverageList",
    "DiscountKB",
    "EgnitionLockKeysCount",
    "EngineHorsePower",
    "EnginePowerMeasured",
    "EngineVolume",
    "IsRegisteredInRF",
    "Make",
    "MarkModelCode",
    "Model",
    "PublicID",
    "RegistrationCountry",
    "UWCoefficientKA",
    "VIN",
    "VehMakeForPrinitng",
    "VehModelForPrinitng",
    "VehicleDocuments",
    "VehicleNumber",
    "VehicleTypeID",
    "VehicleUsagePurpose",
    "Year"
})
public class PCMvehicleDTO {

    @JsonProperty("PublicID")
    private String publicId;

    @JsonProperty("AnnualMileage")
    private Integer annualMileage;
    
    @JsonProperty("AntiTheftSystem")
    private String antiTheftSystem;
    
    @JsonProperty("CarryingCapacity")
    private BigDecimal carryingCapacity;
    
    @JsonProperty("CostNewCur")
    private String costNewCur;
    
    @JsonProperty("CostNew_amt")
    private String costNewAmt;
    
    //@JsonProperty("CoverageList")
    //private List<CoverageList> coverageList = null;
    
    @JsonProperty("DiscountKB")
    private BigDecimal discountKB;
    
    @JsonProperty("EgnitionLockKeysCount")
    private String egnitionLockKeysCount;
    
    @JsonProperty("EngineHorsePower")
    private Integer engineHorsePower;
    
    @JsonProperty("EnginePowerMeasured")
    private String enginePowerMeasured;
    
    @JsonProperty("EngineVolume")
    private BigDecimal engineVolume;
    
    @JsonProperty("IsRegisteredInRF")
    private Boolean isRegisteredInRF;
    
    @JsonProperty("Make")
    private String make;
    
    @JsonProperty("MarkModelCode")
    private String markModelCode;
    
    @JsonProperty("Model")
    private String model;
            
    @JsonProperty("RegistrationCountry")
    private String registrationCountry;
    
    @JsonProperty("UWCoefficientKA")
    private BigDecimal uWCoefficientKA;
    
    @JsonProperty("VIN")
    private String vin;
    
    @JsonProperty("VehMakeForPrinitng")
    private String vehMakeForPrinitng;
    
    @JsonProperty("VehModelForPrinitng")
    private String vehModelForPrinitng;
    
    //@JsonProperty("VehicleDocuments")
    //private VehicleDocuments vehicleDocuments;
    
    @JsonProperty("VehicleNumber")
    private String vehicleNumber;
    
    @JsonProperty("VehicleTypeID")
    private String vehicleTypeID;
    
    @JsonProperty("VehicleUsagePurpose")
    private String vehicleUsagePurpose;
    
    @JsonProperty("Year")
    private Integer year;
}