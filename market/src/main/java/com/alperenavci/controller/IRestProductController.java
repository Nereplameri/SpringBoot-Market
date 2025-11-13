package com.alperenavci.controller;

import com.alperenavci.dto.DtoProduct;
import com.alperenavci.dto.DtoProductIU;
import com.alperenavci.utils.RestPageableEntity;
import com.alperenavci.utils.RestPageableRequest;

public interface IRestProductController {
	public RootEntity<DtoProduct> saveProduct(DtoProductIU inputProduct);
	public RootEntity<DtoProduct> findProductById(Long id);
	public RootEntity<DtoProduct> findProductByName(String name);
	public RootEntity<RestPageableEntity<DtoProduct>> findAllPageable(RestPageableRequest pageable);
	public RootEntity<DtoProduct> findByBarcode(String barcode);
	public RootEntity<DtoProduct> updateProduct(DtoProductIU inputProduct, Long id);
	public RootEntity<String> deleteProduct(Long id);
}