package com.alperenavci.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alperenavci.controller.IRestProductController;
import com.alperenavci.controller.RestBaseController;
import com.alperenavci.controller.RootEntity;
import com.alperenavci.dto.DtoProduct;
import com.alperenavci.dto.DtoProductIU;
import com.alperenavci.dto.DtoReduceQuentity;
import com.alperenavci.entity.Product;
import com.alperenavci.service.IProductService;
import com.alperenavci.utils.RestPageableEntity;
import com.alperenavci.utils.RestPageableRequest;

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
	
	@GetMapping("/list/product")
	@Override
	public RootEntity<RestPageableEntity<DtoProduct>> findAllPageable(@ModelAttribute RestPageableRequest pageable) {
		
		Page<Product> page = productService.findAllPageable(toPageable(pageable), pageable.getBrandId());
		RestPageableEntity<DtoProduct> pageableResponse = toPageableResponse(page, productService.toDtoList(page.getContent()));
		return ok(pageableResponse);
	}
	
	@GetMapping("/getProductByBarcode/{barcode}")
	@Override
	public RootEntity<DtoProduct> findByBarcode(@PathVariable(name = "barcode") String barcode) {
		return ok(productService.findByBarcode(barcode));
	}
	
	@PutMapping("/updateProduct/{id}")
	@Override
	public RootEntity<DtoProduct> updateProduct(@Valid @RequestBody DtoProductIU inputProduct,@PathVariable(name = "id") Long id) {
		return ok(productService.updateProduct(inputProduct, id));
	}

	@DeleteMapping("/deleteProduct/{id}")
	@Override
	public RootEntity<String> deleteProduct(@PathVariable(name = "id") Long id) {
		return ok(productService.deleteProduct(id));
	}

	
	@PostMapping("/reduceQuentity")
	@Override
	public RootEntity<List<DtoProduct>> reduceQuentity(@RequestBody DtoReduceQuentity<Long> request) {
		return ok(productService.reduceQuentity(request));
	}
	
	
	
}
