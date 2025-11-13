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
import com.alperenavci.dto.DtoBrandIU;
import com.alperenavci.entity.BaseEntity;
import com.alperenavci.entity.Brand;
import com.alperenavci.exception.BaseException;
import com.alperenavci.exception.ErrorMessage;
import com.alperenavci.exception.MessageType;
import com.alperenavci.repository.BrandRepository;
import com.alperenavci.service.IBrandService;

@Service
public class BrandServiceImpl implements IBrandService{
	
	@Autowired
	private BrandRepository brandRepository;
	
	/**
	 * 
	 * @param inputBrand
	 * @return a new Brand object
	 */
	
	private Brand createBrand(DtoBrandIU inputBrand) {
		Brand brand = new Brand();
		
		BeanUtils.copyProperties(inputBrand, brand);
		brand.setCreateTime(new Date());
		
		return brand;
	}
	
	/**
	 * 
	 * @param brand
	 * @return a new DtoBrand object
	 */
	public DtoBrand convertBrandToDto(Brand brand) {
		DtoBrand dtoBrand = new DtoBrand();
		BeanUtils.copyProperties(brand, dtoBrand);
		return dtoBrand;
	}
	
	@Override
	public DtoBrand saveBrand(DtoBrandIU inputBrand) {
		
		Optional<Brand> optional = brandRepository.findByName(inputBrand.getName());
		if(optional.isPresent()) {
			throw new BaseException(new ErrorMessage(MessageType.BRAND_NAME_ALREADY_EXIST, inputBrand.getName()));
		}
		
		Brand brand = brandRepository.save(createBrand(inputBrand));
		
		DtoBrand dtoBrand = convertBrandToDto(brand);
		
		return dtoBrand;
	}
	
	@Override
	public DtoBrand findByName(String name) {
		
		Optional<Brand> optBrand = brandRepository.findByName(name);
		if (optBrand.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.BRAND_NOT_FOUND, name));
		}
		
		Brand brand = optBrand.get();
		DtoBrand dtoBrand = convertBrandToDto(brand);
		
		return dtoBrand;
	}
	
	
	public Brand getBrandById(Long id) {
		Optional<Brand> optional = brandRepository.findById(id);
		
		if(optional.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.BRAND_NOT_FOUND, id.toString()));
		}
		
		return optional.get();
	}
	

	
	@Override
	public DtoBrand findBrandById(Long id) {
		
		Brand brand = getBrandById(id);
		
		DtoBrand dtoBrand = convertBrandToDto(brand);
		
		return dtoBrand;
	}

	@Override
	public DtoBrand updateBrand(DtoBrandIU inputBrand, Long id) {
		
		Brand brand = getBrandById(id);
		BeanUtils.copyProperties(inputBrand, brand);
		brandRepository.save(brand);
		
		DtoBrand dtoBrand = convertBrandToDto(brand);
		
		return dtoBrand;
	}

	private void brandIdExistControl(Long id) {
		if (brandRepository.findById(id).isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.BRAND_ID_INVALID, id.toString()));
		}
	}
	
	@Override
	public String deleteBrand(Long id) {
		
		brandIdExistControl(id);
		
		brandRepository.deleteById(id);
		
		return "Succsess";
	}


	@Override
	public Page<Brand> findAllPageable(Pageable pageable) {
		return brandRepository.findAllPageable(pageable);
	}


	@Override
	public List<DtoBrand> toDTOList(List<Brand> brandList) {
		List<DtoBrand> dtoList = new ArrayList<>();
		
		for (Brand brand : brandList) {
			DtoBrand dtoBrand = convertBrandToDto(brand);
			dtoList.add(dtoBrand);
		}
		return dtoList;
	}



	
}
