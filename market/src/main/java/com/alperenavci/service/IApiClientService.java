package com.alperenavci.service;

import com.alperenavci.dto.BarcodeDataFetcher;

public interface IApiClientService {
	public BarcodeDataFetcher barcodeAutoComplate(String barcode);
}
