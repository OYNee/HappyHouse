package com.ssafy.happyhouse.model.repo;

import java.util.List;

import com.ssafy.happyhouse.model.dto.ShoppingMall;
import com.ssafy.happyhouse.model.dto.ShoppingMallPageBean;

public interface ShoppingMallDao {

	public List<ShoppingMall> searchAll(ShoppingMallPageBean bean);

	public ShoppingMall search(int num);
	
	int getTotalCount(String dong);

	public List<ShoppingMall> testSearchAll(ShoppingMallPageBean shoppingMallPageBean);

}
