package com.alperenavci.controller;

import com.alperenavci.dto.BarcodeDataFetcher;

public interface IRestApiClientController {
	public RootEntity<BarcodeDataFetcher> barcodeAutoComplate(String barcode);
}
