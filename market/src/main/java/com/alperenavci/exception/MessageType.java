package com.alperenavci.exception;

import lombok.Getter;

@Getter
public enum MessageType {
	NO_RECORD_EXIST("1004", "Kayıt bulunamadı"),
	TOKEN_IS_EXPIRED("1005", "Tokenin sütesi bitmiştir."),
	USERNAME_NOT_FOUND("1006", "Username bulunamadı"),
	USERNAME_OR_PASSWORD_INVALID("1007", "Kullanıcı adı veya şifre hatalı"),
	REFRESH_TOKEN_NOT_FOUND("1008", "Refresh Token Bulunamadı."),
	REFRESH_TOKEN_IS_EXPIRED("1009", "Refresh Tokenin süresi bitmiştir."),
	BRAND_NOT_FOUND("4010", "Firma bulunamadı."),
	BRAND_ID_INVALID("4011", "Firma numarası geçersiz"),
	BRAND_NAME_ALREADY_EXIST("4012", "Firma ismi daha önce oluşturulmuş."),
	PRODUCT_NAME_ALREADY_EXIST("4013", "Ürün ismi daha önce oluşturulmuş."),
	PRODUCT_ID_INVALID("4014", "Ürün ID 'si geçersiz."),
	PRODUCT_NOT_FOUND("4015", "Ürün bulunamadı"),
	GENERAL_EXCEPTION("9999", "Genel bir hata oluştu");
	
	
	private String code;
	
	private String message;
	
	MessageType(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
}
