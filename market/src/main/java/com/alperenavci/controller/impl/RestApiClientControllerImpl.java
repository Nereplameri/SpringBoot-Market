package com.alperenavci.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alperenavci.controller.IRestApiClientController;
import com.alperenavci.controller.RestBaseController;
import com.alperenavci.controller.RootEntity;
import com.alperenavci.dto.BarcodeDataFetcher;
import com.alperenavci.service.IApiClientService;

@RestController
@RequestMapping("/res/api/client")
public class RestApiClientControllerImpl extends RestBaseController implements IRestApiClientController{

	@Autowired
	private IApiClientService apiClientService;
	
	@GetMapping("/barcodeAutoComplate/{name}")
	@Override
	public RootEntity<BarcodeDataFetcher> barcodeAutoComplate(@PathVariable(name = "name") String barcode) {
		return ok(apiClientService.barcodeAutoComplate(barcode));
	}
	
}
