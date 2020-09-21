package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.ShoppingMall;
import com.ssafy.happyhouse.model.dto.ShoppingMallPageBean;
import com.ssafy.happyhouse.util.PageNavigation;

public interface ShoppingMallService {
	public List<ShoppingMall> searchAll(int currentPage, int sizePerPage, String dong);

	public ShoppingMall search(int num);
	
	public PageNavigation makePageNavigation(int pg, int sizePerPage, String dong) throws Exception;

	public List<ShoppingMall> testSearchAll(ShoppingMallPageBean shoppingMallPageBean);

	

}
