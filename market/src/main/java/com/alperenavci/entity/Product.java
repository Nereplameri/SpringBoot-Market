package com.alperenavci.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class Product extends BaseEntity{
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "purchase_price")
	private Long purchasePrice;
	
	@Column(name = "sell_price")
	private Long sellPrice;
	
	@Column(name = "is_presented")
	private boolean isPresented;
	
	@Column(name = "barcode")
	private String barcode;
	
	@Column(name = "remaining_product_quantity")
	private Long remainingProductQuantity;
	
	@Column(name = "expiration_date")
	private Date expirationDate;
	
	@Column(name = "shelf_alarm_report")
	private Date shelfAlarmReport;
	
	@ManyToOne
	private Brand brand;
}
