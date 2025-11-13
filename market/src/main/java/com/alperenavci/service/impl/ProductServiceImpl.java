package com.alperenavci.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alperenavci.dto.DtoBrand;
import com.alperenavci.dto.DtoProduct;
import com.alperenavci.dto.DtoProductIU;
import com.alperenavci.entity.Brand;
import com.alperenavci.entity.Product;
import com.alperenavci.exception.BaseException;
import com.alperenavci.exception.ErrorMessage;
import com.alperenavci.exception.MessageType;
import com.alperenavci.repository.BrandRepository;
import com.alperenavci.repository.ProductRepository;
import com.alperenavci.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private BrandRepository brandRepository;
	
	private Product createProduct(DtoProductIU inputProduct) {
		Product product = new Product();
		product.setCreateTime(new Date());
		BeanUtils.copyProperties(inputProduct, product);
		// Brand 'ın id sini inceleyelim.
		
		Optional<Brand> optBrand = brandRepository.findById(inputProduct.getBrand());
		if (optBrand.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.BRAND_ID_INVALID, inputProduct.getBrand().toString()));
		}
		
		Brand brand = optBrand.get();
		product.setBrand(brand);
		return product;
	}
	
	/**
	 * 
	 * @param product
	 * @return Dto output versiyonu
	 */
	
	private DtoProduct convertProductToDto(Product product){
		DtoProduct dtoProduct = new DtoProduct();
		BeanUtils.copyProperties(product, dtoProduct);
		
		DtoBrand dtoBrand = new DtoBrand();
		BeanUtils.copyProperties(product.getBrand(), dtoBrand);
		dtoProduct.setBrand(dtoBrand);
		
		return dtoProduct;
	}
	
	@Override
	public DtoProduct saveProduct(DtoProductIU inputProduct) {
		
		if (productRepository.findByName(inputProduct.getName()).isPresent()) {
			throw new BaseException(new ErrorMessage(MessageType.PRODUCT_NAME_ALREADY_EXIST, inputProduct.getName()));
		}
		
		Product product = productRepository.save(createProduct(inputProduct));
		
		DtoProduct dtoProduct = convertProductToDto(product);
		
		return dtoProduct;
	}

	@Override
	public DtoProduct findProductById(Long id) {
		
		Optional<Product> optProduct = productRepository.findById(id);
		
		if(optProduct.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.PRODUCT_ID_INVALID, id.toString()));
		}
		
		DtoProduct dtoProduct = convertProductToDto(optProduct.get());
		
		return dtoProduct;
	}
	

	
	@Override
	public DtoProduct findProductByName(String name) {
		
		Optional<Product> optProduct = productRepository.findByName(name);
		if (optProduct.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.PRODUCT_NOT_FOUND, name));
		}
		
		DtoProduct dtoProduct = convertProductToDto(optProduct.get());
		
		
		return dtoProduct;
	}
	
}
