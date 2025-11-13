package com.alperenavci.service;

import com.alperenavci.dto.DtoProduct;
import com.alperenavci.dto.DtoProductIU;

public interface IProductService {
	public DtoProduct saveProduct(DtoProductIU inputProduct);
	public DtoProduct findProductById(Long id);
	public DtoProduct findProductByName(String name);
}
