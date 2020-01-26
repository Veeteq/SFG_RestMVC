package com.wojnarowicz.sfg.gw.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pc_policies")
public class PCPolicy {

    @Id
    @Column(name = "public_id")
    public String publicId;
    
    public String account;
    public String actionTerritory;
    //public List<AgentInfoList> agentInfoList = null;
    //public AgentStatement agentStatement;
    public Integer branch;
    //public Change change;
    public String checkSumCorrect;
    public String clientSystem;
    public String contractOption;
    public Boolean contractSign;
    
    @Enumerated(value = EnumType.STRING)
    public PCCurrency currency;
    
    public Integer detentionTerritory;
    public String driverPermit;

    @Column(name="start_date", columnDefinition="TIMESTAMP", nullable=true)
    public LocalDateTime startDate;

    @Column(name="end_date", columnDefinition="TIMESTAMP", nullable=true)
    public LocalDateTime endDate;
    
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "pc_insured_id")
    public PCContact insured;
    
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "pc_owner_id", nullable = true)
    public PCContact owner;
        
    public BigDecimal insuredPremium;
    
    @Column(name="insured_premium_date", columnDefinition="DATE", nullable=true)
    public LocalDate insuredPremiumDate;
    
    @Column(name="issue_date", columnDefinition="TIMESTAMP", nullable=true)
    public LocalDateTime issueDate;
    
    public Boolean issued;
        
    @Column(name="liability_start_date", columnDefinition="TIMESTAMP", nullable=true)
    public LocalDateTime liabilityStartDate;

    @Column(name="liability_end_date", columnDefinition="TIMESTAMP", nullable=true)
    public LocalDateTime liabilityEndDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pc_vehicle_id")
    public PCMvehicle mVehicle;
    
    public String offering;    
    
    public String pledgedProperty;
    public String policyNumber;
    public String policyNumberBSO;
    
    public String policySeries;
    public String policySeriesBSO;
    public String policySuffix;
    public String policySystemPeriodId;
    public String product;
    public Integer region;
    public Integer sKKCode;
    public Integer salesChannelType;
        
    public String status;
    public BigDecimal sumInsured;
    public BigDecimal sumInsuredTotal;
    public String sysOp;

}
