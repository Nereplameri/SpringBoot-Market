package com.alperenavci.controller;

import java.util.List;

import com.alperenavci.dto.DtoFreshProduce;
import com.alperenavci.dto.DtoFreshProduceIU;
import com.alperenavci.utils.RestPageableEntity;
import com.alperenavci.utils.RestPageableRequest;

public interface IRestFreshProduceController {
	public RootEntity<DtoFreshProduce> saveFreshProduce(DtoFreshProduceIU inputProduce);
	public RootEntity<DtoFreshProduce> getFreshProduceById(Long id);
	public RootEntity<List<DtoFreshProduce>> getFreshProduceByName(String name);
	public RootEntity<RestPageableEntity<DtoFreshProduce>> findAllPageable(RestPageableRequest pageable);
	public RootEntity<DtoFreshProduce> updateFreshProduce(DtoFreshProduceIU inputProduce, Long id);
	public RootEntity<String> deleteFreshProduce(Long id);
}
