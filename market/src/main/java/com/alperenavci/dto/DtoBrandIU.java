package com.alperenavci.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoBrandIU {
	
	@NotEmpty(message = "İsim boş olamaz")
	private String name;

	private String phoneNumber;

	@Email(message = "Geçerli bir E-mail giriniz")
	private String email;
}
