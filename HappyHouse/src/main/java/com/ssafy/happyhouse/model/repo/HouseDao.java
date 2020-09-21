package com.ssafy.happyhouse.model.repo;

import java.util.List;

import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HousePageBean;

public interface HouseDao {
	
	List<HouseDeal> searchAll(HousePageBean bean);

	HouseDeal search(int no);
	
	int getTotalCount(HousePageBean bean);
	
	public List<HouseDeal> testSearchAll(HousePageBean bean);

	List<HouseDeal> getList(HousePageBean housePageBean);
}
