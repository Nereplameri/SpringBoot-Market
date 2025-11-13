package com.alperenavci.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoProductIU {
	
	@NotEmpty
	private String name;
	
	private Long purchasePrice;
	
	@NotEmpty
	private Long sellPrice;
	
	private boolean isPresented;
	
	@NotEmpty
	private String barcode;
	
	@NotEmpty
	private Long remainingProductQuantity;
	
	@JsonFormat(pattern = "dd.MM.yyyy")
	private Date expirationDate;
	
	@JsonFormat(pattern = "dd.MM.yyyy")
	private Date shelfAlarmReport;
	
	@NotEmpty
	private Long brand;
}
