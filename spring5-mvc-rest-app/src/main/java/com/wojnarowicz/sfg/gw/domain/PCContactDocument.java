package com.wojnarowicz.sfg.gw.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pc_contact_documents")
public class PCContactDocument {

    @Id
    @Column(name = "public_id")
    private String publicId;
    
    private String docNumber;
    
    private LocalDate docStartDate;
    
    private String documentType;
    
    private String issued;
    
    private String serie;
    
    @ManyToOne
    @JoinColumn(name = "contact_id")
    private PCContact contact;
}
