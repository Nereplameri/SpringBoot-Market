package com.alperenavci.controller.impl;

import java.math.BigDecimal;
import java.util.List;

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

import com.alperenavci.controller.IRestFreshProduceController;
import com.alperenavci.controller.RestBaseController;
import com.alperenavci.controller.RootEntity;
import com.alperenavci.dto.DtoFreshProduce;
import com.alperenavci.dto.DtoFreshProduceIU;
import com.alperenavci.dto.DtoReduceQuentity;
import com.alperenavci.entity.FreshProduce;
import com.alperenavci.service.IFreshProduceService;
import com.alperenavci.utils.RestPageableEntity;
import com.alperenavci.utils.RestPageableRequest;

@RestController
@RequestMapping("/rest/api/freshProduce")
public class RestFreshProduceControllerImpl extends RestBaseController implements IRestFreshProduceController{
	
	@Autowired
	private IFreshProduceService freshProduceService;
	
	@PostMapping("/saveFreshProduce")
	@Override
	public RootEntity<DtoFreshProduce> saveFreshProduce(@RequestBody DtoFreshProduceIU inputProduce) {
		return ok(freshProduceService.saveFreshProduce(inputProduce));
	}

	@GetMapping("/getById/{id}")
	@Override
	public RootEntity<DtoFreshProduce> getFreshProduceById(@PathVariable(name = "id") Long id) {
		return ok(freshProduceService.findFreshProduceById(id));
	}

	@GetMapping("/getByName/{name}")
	@Override
	public RootEntity<List<DtoFreshProduce>> getFreshProduceByName(@PathVariable(name = "name") String name) {
		return ok(freshProduceService.getFreshProduceByName(name));
	}
	
	@GetMapping("/list/freshProduce")
	@Override
	public RootEntity<RestPageableEntity<DtoFreshProduce>> findAllPageable(@ModelAttribute RestPageableRequest pageable) {
		Page<FreshProduce> page = freshProduceService.findAllPageable(toPageable(pageable), pageable.getBrandId());
		RestPageableEntity<DtoFreshProduce> pageableResponse = toPageableResponse(page, freshProduceService.toDtoList(page.getContent()));
		return ok(pageableResponse);
	}
	
	@PutMapping("/updateFreshProduce/{id}")
	@Override
	public RootEntity<DtoFreshProduce> updateFreshProduce(@RequestBody DtoFreshProduceIU inputProduce,@PathVariable Long id) {
		return ok(freshProduceService.updateFreshProduce(inputProduce, id));
	}

	@DeleteMapping("/deleteFreshProduce/{id}")
	@Override
	public RootEntity<String> deleteFreshProduce(@PathVariable(name = "id") Long id) {
		return ok(freshProduceService.deleteFreshProduce(id));
	}
	
	@PostMapping("/reduceQuentity")
	@Override
	public RootEntity<List<DtoFreshProduce>> reduceQuentity(@RequestBody DtoReduceQuentity<BigDecimal> request) {
		return ok(freshProduceService.reduceQuentity(request));
	}
	
}
