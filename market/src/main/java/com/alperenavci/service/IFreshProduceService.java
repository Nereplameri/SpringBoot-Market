package com.alperenavci.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alperenavci.dto.DtoFreshProduce;
import com.alperenavci.dto.DtoFreshProduceIU;
import com.alperenavci.entity.FreshProduce;

public interface IFreshProduceService {
	public DtoFreshProduce saveFreshProduce(DtoFreshProduceIU inputProduce);
	public DtoFreshProduce findFreshProduceById(Long id);
	public List<DtoFreshProduce> getFreshProduceByName(String name);
	public Page<FreshProduce> findAllPageable(Pageable pageable);
	public List<DtoFreshProduce> toDtoList (List<FreshProduce> produceList);
	public DtoFreshProduce updateFreshProduce(DtoFreshProduceIU inputProduce, Long id);
	public String deleteFreshProduce(Long id);
}
