package com.alperenavci.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoProduct extends DtoBase{
	
	private String name;
	
	private BigDecimal purchasePrice;
	
	private BigDecimal sellPrice;
	
	private boolean isPresented;
	
	private String barcode;
	
	private Long remainingProductQuantity;
	
	private Date expirationDate;
	
	private Date shelfAlarmReport;
	
	private DtoBrand brand;
}
