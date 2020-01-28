package com.wojnarowicz.sfg.gw.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "agents")
public class Agent {

    private static final String DELIMITER = " ";

    @Id
    @Column(name="agent_code")
    private Long code;

    @Column(name="LNR")
    private Long lnr;
    
    @Column(name="SKK")
    private Long skk;
    
    @Column(name="FIRST_NAME")
    private String firstName;

    @Column(name="MIDDLE_NAME")
    private String middleName;

    @Column(name="LAST_NAME")
    private String lastName;

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy="agent")
    private List<BsoDocument> bsoDocuments = new ArrayList<>();
    
    public void addBso(BsoDocument bsoDocument) {
        bsoDocument.setAgent(this);
        bsoDocuments.add(bsoDocument);
    }

    public String getName() {
        return Arrays.asList(this.firstName, this.lastName)
        .stream()
        .filter(el -> el != null).collect(Collectors.joining(DELIMITER));
    }    
}
