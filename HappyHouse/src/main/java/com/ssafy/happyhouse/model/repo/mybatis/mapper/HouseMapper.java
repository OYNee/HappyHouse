package com.ssafy.happyhouse.model.repo.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HousePageBean;

@Mapper
public interface HouseMapper {
	
	List<HouseDeal> searchAll(HousePageBean bean);

	HouseDeal search(int no);

}
