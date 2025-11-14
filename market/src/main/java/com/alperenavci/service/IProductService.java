package com.alperenavci.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alperenavci.dto.DtoProduct;
import com.alperenavci.dto.DtoProductIU;
import com.alperenavci.dto.DtoReduceQuentity;
import com.alperenavci.entity.Product;

public interface IProductService {
	public DtoProduct saveProduct(DtoProductIU inputProduct);
	public DtoProduct findProductById(Long id);
	public DtoProduct findProductByName(String name);
	public Page<Product> findAllPageable(Pageable pageable, Long brandId);
	public List<DtoProduct> toDtoList(List<Product> productList);
	public DtoProduct findByBarcode(String barcode);
	public DtoProduct updateProduct(DtoProductIU inputProduct, Long id);
	public String deleteProduct(Long id);
	public List<DtoProduct> reduceQuentity(DtoReduceQuentity<Long> request);
}