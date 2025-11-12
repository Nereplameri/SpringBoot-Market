package com.alperenavci.controller;

import com.alperenavci.dto.DtoBrand;
import com.alperenavci.dto.DtoBrandIU;
import com.alperenavci.utils.RestPageableEntity;
import com.alperenavci.utils.RestPageableRequest;

public interface IRestBrandController {
	public RootEntity<DtoBrand> saveBrand(DtoBrandIU inputBrand);
	public RootEntity<DtoBrand> findBrandById(Long id);
	public RootEntity<DtoBrand> findByName(String name);
	public RootEntity<DtoBrand> updateBrand(DtoBrandIU inputBrand, Long id);
	public RootEntity<String> deleteBrand(Long id);
	public RootEntity<RestPageableEntity<DtoBrand>> findAllPageable(RestPageableRequest pageable);
}
