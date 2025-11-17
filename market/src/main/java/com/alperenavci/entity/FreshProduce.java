package com.alperenavci.entity;

import java.math.BigDecimal;

import com.alperenavci.enums.UnitType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fresh_produce")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FreshProduce extends BaseEntity{
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "quantity")
	private BigDecimal quantity;
	
	@Column(name = "unit_type")
	@Enumerated(EnumType.STRING)
	private UnitType unitType;
	
	@Column(name = "unit_purchase_price")
	private BigDecimal unitPurchasePrice;
	
	@Column(name = "unit_sell_price")
	private BigDecimal unitSellPrice;
	
	@ManyToOne
	private Brand brand;
}
