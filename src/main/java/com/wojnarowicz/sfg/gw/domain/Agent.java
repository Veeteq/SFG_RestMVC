package com.wojnarowicz.sfg.gw.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "agents")
@AttributeOverride(name="id", column=@Column(name="agent_id"))
@SequenceGenerator(name="default_seq", sequenceName="agent_seq", allocationSize=1)
public class Agent extends BaseEntity {

    private static final long serialVersionUID = 1L;
    private static final String DELIMITER = " ";

    @Column(name="CODE")
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

    @OneToMany(cascade=CascadeType.ALL, mappedBy="agent")
    private Set<BsoDocument> bsoDocuments = new HashSet<>();
    
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
