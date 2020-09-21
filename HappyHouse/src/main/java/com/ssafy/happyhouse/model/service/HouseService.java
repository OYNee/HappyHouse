package com.ssafy.happyhouse.model.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HousePageBean;
import com.ssafy.happyhouse.util.PageNavigation;

public interface HouseService {
	public List<HouseDeal> searchAll(int pg, int sizePerPage, HousePageBean bean);
	public HouseDeal search(int no) throws UnsupportedEncodingException;
	public PageNavigation makePageNavigation(int pg, int sizePerPage, HousePageBean bean) throws Exception;
	public List<HouseDeal> testSearchAll(HousePageBean bean);
	public List<HouseDeal> getList(HousePageBean housePageBean);
}
