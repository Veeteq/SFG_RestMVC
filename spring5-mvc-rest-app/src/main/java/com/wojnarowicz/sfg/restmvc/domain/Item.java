package com.wojnarowicz.sfg.restmvc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Setter;

import lombok.Getter;

@Getter
@Setter
@Entity
@Table(name = "items")
public class Item {

	@Id
	@Column(name = "item_id")
	private String id;
	
	@Column(name = "item_name")
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="category_id", nullable=true)
	private Category category;
}
