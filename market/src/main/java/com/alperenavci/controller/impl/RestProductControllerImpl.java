package com.alperenavci.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alperenavci.controller.IRestProductController;
import com.alperenavci.controller.RestBaseController;
import com.alperenavci.controller.RootEntity;
import com.alperenavci.dto.DtoProduct;
import com.alperenavci.dto.DtoProductIU;
import com.alperenavci.service.IProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/product")
public class RestProductControllerImpl extends RestBaseController implements IRestProductController{
	
	@Autowired
	private IProductService productService;
	
	@PostMapping("/addProduct")
	@Override
	public RootEntity<DtoProduct> saveProduct(@Valid @RequestBody DtoProductIU inputProduct) {
		return ok(productService.saveProduct(inputProduct));
	}

	@GetMapping("/getProductById/{id}")
	@Override
	public RootEntity<DtoProduct> findProductById(@PathVariable(name = "id") Long id) {
		return ok(productService.findProductById(id));
	}

	@GetMapping("/getProductByName/{name}")
	@Override
	public RootEntity<DtoProduct> findProductByName(@PathVariable(name = "name") String name) {
		return ok(productService.findProductByName(name));
	}
	
}
