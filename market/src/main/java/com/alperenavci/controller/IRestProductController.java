package com.alperenavci.controller;

import com.alperenavci.dto.DtoProduct;
import com.alperenavci.dto.DtoProductIU;
import com.alperenavci.utils.RestPageableEntity;

public interface IRestProductController {
	public RootEntity<DtoProduct> saveProduct(DtoProductIU inputProduct);
	public RootEntity<DtoProduct> findProductById(Long id);
	public RootEntity<DtoProduct> findProductByName(String name);
	
}
