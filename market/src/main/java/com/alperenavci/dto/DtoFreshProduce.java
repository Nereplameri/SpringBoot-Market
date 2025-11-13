package com.alperenavci.dto;

import java.math.BigDecimal;

import com.alperenavci.enums.UnitType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoFreshProduce extends DtoBase{

	private String name;
	
	private BigDecimal quantity;
	
	private UnitType unitType;
	
	private Long unitPurchasePrice;
	
	private Long unitSellPrice;
	
	private DtoBrand brand;
	
}
