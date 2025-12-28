package com.alperenavci.service.impl;

import java.math.BigDecimal;
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
import com.alperenavci.dto.DtoFreshProduce;
import com.alperenavci.dto.DtoFreshProduceIU;
import com.alperenavci.dto.DtoReduceQuentity;
import com.alperenavci.dto.ReduceQuentityRequest;
import com.alperenavci.entity.Brand;
import com.alperenavci.entity.FreshProduce;
import com.alperenavci.exception.BaseException;
import com.alperenavci.exception.ErrorMessage;
import com.alperenavci.exception.MessageType;
import com.alperenavci.repository.BrandRepository;
import com.alperenavci.repository.FreshProduceRepository;
import com.alperenavci.service.IFreshProduceService;

@Service
public class FreshProduceServiceImpl implements IFreshProduceService{
	
	@Autowired
	private FreshProduceRepository freshProduceRepository;
	
	@Autowired
	private BrandRepository brandRepository;
	
	/**
	 * 
	 * @param inputProduce
	 * @return a new FreshProduce object
	 */
	private FreshProduce createFreshProduce(DtoFreshProduceIU inputProduce) {
		FreshProduce freshProduce = new FreshProduce();
		freshProduce.setCreateTime(new Date());
		BeanUtils.copyProperties(inputProduce, freshProduce);
		
		
		Optional<Brand> optBrand = brandRepository.findById(inputProduce.getBrand());
		if(optBrand.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.BRAND_NOT_FOUND, inputProduce.getBrand().toString()));
		}
		
		freshProduce.setBrand(optBrand.get());
		return freshProduce;
	}
	
	/**
	 * Creates a Dto version of FreshProduce
	 * @param inputProduce
	 * @return DtoFreshProduce
	 */
	private DtoFreshProduce convertFreshProduceToDto(FreshProduce inputProduce) {
		DtoFreshProduce dtoFreshProduce = new DtoFreshProduce();
		BeanUtils.copyProperties(inputProduce, dtoFreshProduce);
		
		DtoBrand dtoBrand = new DtoBrand();
		BeanUtils.copyProperties(inputProduce.getBrand(), dtoBrand);
		
		dtoFreshProduce.setBrand(dtoBrand);
		return dtoFreshProduce;
	}
	
	/**
	 * 
	 * @param id
	 * @return FreshProduce 's database where matches its id
	 */
	private FreshProduce getFreshProduceById(Long id) {
		Optional<FreshProduce> optProduce = freshProduceRepository.findById(id);
		if(optProduce.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.FRESH_PRODUCE_NOT_FOUND, id.toString()));
		}
		return optProduce.get();
	}
	
	@Override
	public DtoFreshProduce saveFreshProduce(DtoFreshProduceIU inputProduce) {
		FreshProduce freshProduce = freshProduceRepository.save(createFreshProduce(inputProduce));
		
		DtoFreshProduce dtoFreshProduce = convertFreshProduceToDto(freshProduce);
		
		return dtoFreshProduce;
	}

	@Override
	public DtoFreshProduce findFreshProduceById(Long id) {		
		FreshProduce freshProduce = getFreshProduceById(id);
		
		DtoFreshProduce dtoFreshProduce = convertFreshProduceToDto(freshProduce);
		
		return dtoFreshProduce;
	}

	@Override
	public List<DtoFreshProduce> getFreshProduceByName(String name) {
		
		List<FreshProduce> listFreshProduces = freshProduceRepository.findByNameAll(name);
		
		List<DtoFreshProduce> dtoList = new ArrayList<>();
		for (FreshProduce freshProduce : listFreshProduces) {
			DtoFreshProduce dtoFreshProduce = convertFreshProduceToDto(freshProduce);
			dtoList.add(dtoFreshProduce);
		}
		
		return dtoList;
	}

	@Override
	public Page<FreshProduce> findAllPageable(Pageable pageable, Long brandId) {
		
		if (brandId == null) {
			return freshProduceRepository.findAllPageable(pageable);
		}
		return freshProduceRepository.findAllByBrand(brandId, pageable);
	}

	@Override
	public List<DtoFreshProduce> toDtoList(List<FreshProduce> produceList) {
		List<DtoFreshProduce> dtoList = new ArrayList<>();
		
		for (FreshProduce freshProduce : produceList) {
			DtoFreshProduce dtoFreshProduce = convertFreshProduceToDto(freshProduce);
			dtoList.add(dtoFreshProduce);
		}
		
		return dtoList;
	}

	@Override
	public DtoFreshProduce updateFreshProduce(DtoFreshProduceIU inputProduce, Long id) {
		FreshProduce freshProduce = getFreshProduceById(id);
		BeanUtils.copyProperties(inputProduce, freshProduce);
		
		Optional<Brand> optional = brandRepository.findById(inputProduce.getBrand());
		
		
		Brand newBrand;
		if (optional.isPresent()) {
			newBrand = optional.get();
		} else {
			throw new BaseException(new ErrorMessage(MessageType.BRAND_ID_INVALID, inputProduce.getBrand().toString()));
		}
		
		freshProduce.setBrand(newBrand);
		
		freshProduceRepository.save(freshProduce);
		DtoFreshProduce dtoFreshProduce = convertFreshProduceToDto(freshProduce);
		return dtoFreshProduce;
	}

	@Override
	public String deleteFreshProduce(Long id) {
		Optional<FreshProduce> optional = freshProduceRepository.findById(id);
		if (optional.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.FRESH_PRODUCE_NOT_FOUND, id.toString()));
		}
		
		freshProduceRepository.deleteById(id);
		
		return "Succsess";
	}

	@Override
	public List<DtoFreshProduce> reduceQuentity(DtoReduceQuentity<BigDecimal> request) {
		List<DtoFreshProduce> dtoList = new ArrayList<>();
		
		for (ReduceQuentityRequest<BigDecimal> reduceItem: request.getDecreases()) {
			
			FreshProduce freshProduce = getFreshProduceById(reduceItem.getPrimaryId());
			BigDecimal sumQuentity = freshProduce.getQuantity().subtract(reduceItem.getDecreaseBy());
			
			if(sumQuentity.longValue() < 0) {
				throw new BaseException(new ErrorMessage(MessageType.QUENTITY_IS_NOT_ENOUGH, 
									freshProduce.getName() + " : " + sumQuentity.toString()));
			}
			
			freshProduce.setQuantity(sumQuentity);
			freshProduceRepository.save(freshProduce);
			
			DtoFreshProduce dtoFreshProduce = convertFreshProduceToDto(freshProduce);
			dtoList.add(dtoFreshProduce);
		}
		
		return dtoList;
	}

}
