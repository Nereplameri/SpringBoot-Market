package com.alperenavci.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alperenavci.dto.DtoBrand;
import com.alperenavci.dto.DtoBrandIU;
import com.alperenavci.entity.Brand;

public interface IBrandService {
	public DtoBrand saveBrand(DtoBrandIU inputBrand);
	public DtoBrand findByName(String name);
	public DtoBrand findBrandById(Long id);
	public DtoBrand updateBrand(DtoBrandIU inputBrand, Long id);
	public String deleteBrand(Long id);
	public Page<Brand> findAllPageable(Pageable pageable);
	public List<DtoBrand> toDTOList(List<Brand> brandList);
}
