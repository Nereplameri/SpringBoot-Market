package com.alperenavci.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alperenavci.controller.IRestBrandController;
import com.alperenavci.controller.RestBaseController;
import com.alperenavci.controller.RootEntity;
import com.alperenavci.dto.DtoBrand;
import com.alperenavci.dto.DtoBrandIU;
import com.alperenavci.entity.Brand;
import com.alperenavci.service.IBrandService;
import com.alperenavci.utils.RestPageableEntity;
import com.alperenavci.utils.RestPageableRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api")
public class RestBrandControllerImpl extends RestBaseController implements IRestBrandController{
	
	@Autowired
	private IBrandService brandService;
	
	@PostMapping("/saveBrand")
	@Override
	public RootEntity<DtoBrand> saveBrand(@Valid @RequestBody DtoBrandIU inputBrand) {
		return ok(brandService.saveBrand(inputBrand));
	}
	
	@GetMapping("/getBrandName/{name}")
	@Override
	public RootEntity<DtoBrand> findByName(@PathVariable(name = "name") String name) {
		return ok(brandService.findByName(name));
	}
	
	@GetMapping("/getBrandId/{id}")
	@Override
	public RootEntity<DtoBrand> findBrandById(@PathVariable(name = "id") Long id) {
		return ok(brandService.findBrandById(id));
	}
	
	@PutMapping("/updateBrand/{id}")
	@Override
	public RootEntity<DtoBrand> updateBrand(@Valid @RequestBody DtoBrandIU inputBrand, @PathVariable(name = "id") Long id) {
		return ok(brandService.updateBrand(inputBrand, id));
	}

	@DeleteMapping("/deleteBrand/{id}")
	@Override
	public RootEntity<String> deleteBrand(@PathVariable Long id) {
		return ok(brandService.deleteBrand(id));
	}

	@GetMapping("/list/brand")
	@Override
	public RootEntity<RestPageableEntity<DtoBrand>> findAllPageable(@ModelAttribute RestPageableRequest pageable) {
		
		Page<Brand> page = brandService.findAllPageable(toPageable(pageable));
		RestPageableEntity<DtoBrand> pageableResponse = toPageableResponse(page, brandService.toDTOList(page.getContent()));
		return ok(pageableResponse);
	}

	


	
	
}
