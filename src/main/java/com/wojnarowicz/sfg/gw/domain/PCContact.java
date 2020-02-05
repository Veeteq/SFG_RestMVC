package com.wojnarowicz.sfg.gw.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pc_contacts")
public class PCContact {

    @Id
    @Column(name = "public_id")
    private String publicId;
    
    @Embedded
    private PCAddress address;
    
    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL)
    private List<PCContactDocument> contactDocumentList = new ArrayList<>();
    
    @Embedded
    private PCContactMail contactMail;
    
    @Embedded
    private PCContactPhone contactPhone;
    
    private String contactSubtype;
    private String country;
    private LocalDate dateOfBirth;
    private String firstName;
    private String fullName;
    private String gender;
    private String lastName;
    private String middleName;
    private String resident;
    
    
    //@NotFound(action = NotFoundAction.IGNORE)
    // //(cascade = {CascadeType.MERGE}, )
    @OneToMany(mappedBy = "owner")
    private List<PCPolicy> policies = new ArrayList<PCPolicy>();
}
