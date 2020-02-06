package com.wojnarowicz.sfg.restmvc.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "categories")
@Data
public class Category {

    @Id    
    private String id;
    
    @Column(name = "category_name")
    private String name;
    
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "category")
    private List<Item> items = new ArrayList<>();

    public void addToItems(Item item) {
    	item.setCategory(this);
    	this.items.add(item);
    }
}
