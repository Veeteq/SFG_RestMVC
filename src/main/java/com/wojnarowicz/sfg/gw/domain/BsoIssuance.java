package com.wojnarowicz.sfg.gw.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bos_issuances")
public class BsoIssuance {

    @Id
    private Long id;
}
