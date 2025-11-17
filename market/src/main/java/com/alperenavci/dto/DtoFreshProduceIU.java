package com.alperenavci.dto;

import java.math.BigDecimal;

import com.alperenavci.annotation.EnumNamePattern;
import com.alperenavci.enums.UnitType;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DtoFreshProduceIU {
	
	@NotEmpty(message = "İsim boş bırakılamaz")
	private String name;
	
	@NotNull(message = "Manav adeti boş bırakılamaz")
	private BigDecimal quantity;
	
	@EnumNamePattern(regexp = "KG|PIECE")
	private UnitType unitType;
	
	private Long unitPurchasePrice;
	
	@NotNull(message = "Manav fiyatı boş bırakılamaz")
	private Long unitSellPrice;
	
	@NotNull(message = "Manav firması boş bırakılamaz")
	private Long brand;
}
