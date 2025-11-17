package com.alperenavci.entity;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity{
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "purchase_price")
	private BigDecimal purchasePrice;
	
	@Column(name = "sell_price")
	private BigDecimal sellPrice;
	
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
