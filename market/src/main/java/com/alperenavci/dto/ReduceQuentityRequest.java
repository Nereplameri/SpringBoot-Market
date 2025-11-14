package com.alperenavci.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReduceQuentityRequest <T>{
	Long primaryId;
	T decreaseBy;
}
