package com.alperenavci.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoProductIU {
	
	@NotEmpty(message = "Ürün ismi boş bırakılamaz")
	private String name;
	
	private BigDecimal purchasePrice;
	
	@NotNull(message = "Ürün ücreti boş bırakılamaz")
	private BigDecimal sellPrice;
	
	private boolean isPresented;
	
	@NotEmpty(message = "Barkod boş bırakılamaz")
	private String barcode;
	
	@NotNull(message = "Ürün adeti boş bırakılamaz")
	private Long remainingProductQuantity;
	
	@JsonFormat(pattern = "dd.MM.yyyy")
	private Date expirationDate;
	
	@JsonFormat(pattern = "dd.MM.yyyy")
	private Date shelfAlarmReport;
	
	@NotNull(message = "Ürünün markası boş bırakılamaz")
	private Long brand;
}
