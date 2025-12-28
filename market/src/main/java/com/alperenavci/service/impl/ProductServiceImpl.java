package com.alperenavci.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alperenavci.dto.DtoBrand;
import com.alperenavci.dto.DtoProduct;
import com.alperenavci.dto.DtoProductIU;
import com.alperenavci.dto.DtoReduceQuentity;
import com.alperenavci.dto.ReduceQuentityRequest;
import com.alperenavci.entity.Brand;
import com.alperenavci.entity.Product;
import com.alperenavci.exception.BaseException;
import com.alperenavci.exception.ErrorMessage;
import com.alperenavci.exception.MessageType;
import com.alperenavci.repository.BrandRepository;
import com.alperenavci.repository.ProductRepository;
import com.alperenavci.service.IProductService;

import jakarta.annotation.Nullable;

@Service
public class ProductServiceImpl implements IProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private BrandRepository brandRepository;
	
	/**
	 * 
	 * @param inputProduct
	 * @return a new Product
	 */
	
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
	 * @param id
	 * @return Product that maches with "id"
	 */
	
	private Product getProductById(Long id) {
		Optional<Product> optProduct = productRepository.findById(id);
		
		if(optProduct.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.PRODUCT_NOT_FOUND, id.toString()));
		}
		
		return optProduct.get();
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
		
		if (productRepository.findByBarcode(inputProduct.getBarcode()).isPresent()) {
			throw new BaseException(new ErrorMessage(MessageType.PRODUCT_BARCODE_ALREADY_EXIST, inputProduct.getBarcode()));
		}
		
		Product product = productRepository.save(createProduct(inputProduct));
		
		DtoProduct dtoProduct = convertProductToDto(product);
		
		return dtoProduct;
	}

	@Override
	public DtoProduct findProductById(Long id) {
		
		Product product = getProductById(id);
		
		DtoProduct dtoProduct = convertProductToDto(product);
		
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

	@Override
	public Page<Product> findAllPageable(Pageable pageable, @Nullable Long brandId) {
		// Burada ifli koşul!
		// return productRepository.findAllByBrand(brandId, pageable);
		
		if (brandId == null) {
			return productRepository.findAllPageable(pageable);
		}
		return productRepository.findAllByBrand(brandId, pageable);
	}

	@Override
	public List<DtoProduct> toDtoList(List<Product> productList) {
		List<DtoProduct> dtoList = new ArrayList<>();
		
		for (Product product : productList) {
			DtoProduct dtoProduct = convertProductToDto(product);
			dtoList.add(dtoProduct);
		}
		
		return dtoList;
	}
	
	
	
	@Override
	public DtoProduct findByBarcode(String barcode) {
		Optional<Product> optProduct = productRepository.findByBarcode(barcode);
		
		if(optProduct.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.PRODUCT_NOT_FOUND, barcode));
		}
		
		DtoProduct dtoProduct = convertProductToDto(optProduct.get());
		return dtoProduct;
	}

	@Override
	public DtoProduct updateProduct(DtoProductIU inputProduct, Long id) {
		Product product = getProductById(id);
		BeanUtils.copyProperties(inputProduct, product);
		// Burada, firmanın kendisini de değiştireceğiz.
		
		Optional<Brand> optional = brandRepository.findById(inputProduct.getBrand());
		
		Brand newBrand;
		if (optional.isPresent()) {
			newBrand = optional.get();
		} else {
			throw new BaseException(new ErrorMessage(MessageType.BRAND_ID_INVALID, inputProduct.getBrand().toString()));
		}
		
		product.setBrand(newBrand);
		
		
		Product savedProduct = productRepository.save(product);
		DtoProduct dtoProduct = convertProductToDto(savedProduct);
		return dtoProduct;
	}
	
	private void productIdCheck(Long id) {
		Optional<Product> optional = productRepository.findById(id);
		if (optional.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.PRODUCT_NOT_FOUND, id.toString()));
		}
	}
	
	@Override
	public String deleteProduct(Long id) {
		productIdCheck(id);
		
		productRepository.deleteById(id);
		
		return "Succsess";
	}

	@Override
	public List<DtoProduct> reduceQuentity(DtoReduceQuentity<Long> request) {
		List<DtoProduct> dtoList = new ArrayList<>();
		
		for (ReduceQuentityRequest<Long> reduceItem : request.getDecreases()) {
			
			Product product = getProductById(reduceItem.getPrimaryId());
			Long sumQuantity = product.getRemainingProductQuantity() - reduceItem.getDecreaseBy();
			
			if(sumQuantity < 0) {
				throw new BaseException(new ErrorMessage(MessageType.QUENTITY_IS_NOT_ENOUGH, 
										product.getName() + " : "  + sumQuantity.toString()));
			}
			
			product.setRemainingProductQuantity(sumQuantity);
			productRepository.save(product);
			
			DtoProduct dtoProduct = convertProductToDto(product);
			dtoList.add(dtoProduct);
		}
		
		return dtoList;
	}
	
}
