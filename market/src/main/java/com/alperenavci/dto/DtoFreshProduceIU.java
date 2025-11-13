package com.alperenavci.dto;

import java.math.BigDecimal;

import com.alperenavci.enums.UnitType;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class DtoFreshProduceIU {
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private BigDecimal quantity;
	
	@NotEmpty
	private UnitType unitType;
	
	@NotEmpty
	private Long unitPurchasePrice;
	
	@NotEmpty
	private Long unitSellPrice;
	
	@NotEmpty
	private Long brand;
}
