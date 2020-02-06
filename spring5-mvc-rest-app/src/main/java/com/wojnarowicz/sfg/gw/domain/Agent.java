package com.wojnarowicz.sfg.gw.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "agents")
@AttributeOverride(name="id", column=@Column(name="agent_code"))
@GenericGenerator(name = "default_seq", 
strategy = "com.wojnarowicz.sfg.gw.domain.CustomSequenceGenerator", 
parameters = {@Parameter(name="sequence_name", value="agent_seq"),
              @Parameter(name = "increment_size", value = "1")})
public class Agent extends NumericEntity {
    private static final long serialVersionUID = 1L;

    private static final String DELIMITER = " ";

    @Column(name="lnr")
    private Long lnr;
    
    @Column(name="skk")
    private Long skk;
    
    @Column(name="first_name")
    private String firstName;

    @Column(name="middle_name")
    private String middleName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;
    
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy="agent")
    private List<BsoDocument> bsoDocuments = new ArrayList<>();
    
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy="agent", orphanRemoval = false)
    private List<BCPaymentAgent> paymentAgents = new ArrayList<>();
    
    public void addBso(BsoDocument bsoDocument) {
        bsoDocument.setAgent(this);
        bsoDocuments.add(bsoDocument);
    }

    public String getName() {
        return Arrays.asList(this.firstName, this.middleName, this.lastName)
        .stream()
        .filter(el -> el != null).collect(Collectors.joining(DELIMITER));
    }
    
    public Long getCode() {
        return super.getId();
    }
    
    public void setCode(Long code) {
        super.setId(code);
    }

    
    public Agent() {
    }

    @Builder
    public Agent(Long code, Long lnr, Long skk, String firstName, String middleName, String lastName, String email) {
        super(code);
        this.lnr = lnr;
        this.skk = skk;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
    }
}
