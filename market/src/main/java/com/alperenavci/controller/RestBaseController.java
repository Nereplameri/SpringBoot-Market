package com.alperenavci.controller;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alperenavci.utils.PagerUtil;
import com.alperenavci.utils.RestPageableEntity;
import com.alperenavci.utils.RestPageableRequest;

public class RestBaseController {
	
	public <T> RootEntity<T> ok(T payload) {
		return RootEntity.ok(payload);
	}
	
	public <T> RootEntity<T> error(String errorMessage){
		return RootEntity.error(errorMessage);
	}
	
	public Pageable toPageable(RestPageableRequest request) {
		return PagerUtil.toPageable(request);
	}
	
	public <T> RestPageableEntity<T> toPageableResponse(Page<?> page, List<T> content){
		return PagerUtil.toPageableResponse(page, content);
	}

}
