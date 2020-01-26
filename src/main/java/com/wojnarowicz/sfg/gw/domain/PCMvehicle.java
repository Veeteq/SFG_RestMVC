package com.wojnarowicz.sfg.gw.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pc_vehicles")
public class PCMvehicle {

    @Id
    @Column(name = "public_id")
    private String publicId;
    
    @OneToOne(mappedBy = "mVehicle")
    private PCPolicy policy;
    
    private Integer annualMileage;
    private String antiTheftSystem;
    private BigDecimal carryingCapacity;
    private String costNewCur;
    private BigDecimal costNewAmt;
    //private List<CoverageList> coverageList = null;
    private BigDecimal discountKB;
    private String egnitionLockKeysCount;
    private Integer engineHorsePower;
    private String enginePowerMeasured;
    private BigDecimal engineVolume;
    private Boolean isRegisteredInRF;
    private String make;
    private String markModelCode;
    private String model;
    private String registrationCountry;
    private BigDecimal uWCoefficientKA;
    private String vin;
    private String vehMakeForPrinitng;
    private String vehModelForPrinitng;
    //private VehicleDocuments vehicleDocuments;
    private String vehicleNumber;
    private String vehicleTypeID;
    private String vehicleUsagePurpose;
    private Integer year;
    
}
