package com.alperenavci.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alperenavci.dto.BarcodeDataFetcher;
import com.alperenavci.exception.BaseException;
import com.alperenavci.exception.ErrorMessage;
import com.alperenavci.exception.MessageType;
import com.alperenavci.service.IApiClientService;

@Service
public class ApiClientServiceImpl implements IApiClientService{

	@Override
	public BarcodeDataFetcher barcodeAutoComplate(String barcode) {
		
		String rootURL = "https://nhghk.jojapi.net/api/external/search?query=";
		String filler = "&marketPrices=true&preferredMarkets=A101%2C%C5%9Eok+Market";
		String endpoint = rootURL + barcode + filler;
		
		String accsessKey = "jk_R86eddbb20b97add7K10at43m34nqbN69kbf4Meb2e06q5OfwpQJBwpn43Xcw01f";
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Authorization", "Bearer " + accsessKey);
		
		HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
		
		try {
			
			RestTemplate restTemplate = new RestTemplate();
			
			ResponseEntity<List<LinkedHashMap<String, Object>>> response =
			        restTemplate.exchange(
			                endpoint,
			                HttpMethod.GET,
			                httpEntity,
			                new ParameterizedTypeReference<List<LinkedHashMap<String, Object>>>() {}
			        );
			List<LinkedHashMap<String, Object>> body = response.getBody();
			
			if (body == null || body.isEmpty()) {
			    throw new BaseException(new ErrorMessage(MessageType.BARCODE_FETCHER_IS_OCCURED, "Boş veri döndü"));
			}
			
			LinkedHashMap<String, Object> item = body.get(0);
			
			BarcodeDataFetcher result = new BarcodeDataFetcher();
			result.setName((String) item.get("name"));
			result.setBrand((String) item.get("brand"));
			result.setTotal(String.valueOf(item.get("total")));

			return result;
			
		} catch (Exception e) {
			throw new BaseException(new ErrorMessage(MessageType.BARCODE_FETCHER_IS_OCCURED, e.getMessage()));
		}
	}
	
}
