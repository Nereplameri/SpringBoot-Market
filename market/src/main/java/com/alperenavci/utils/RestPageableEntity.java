package com.alperenavci.utils;

import java.util.List;

import lombok.Data;

// Temiz output Json verisi
@Data
public class RestPageableEntity<T> {
	
	private List<T> content; //DTO
	private int pageNumber;
	private int pageSize;
	private Long totalElements;
	
}
