package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.dto.SidoCodeDto;

public interface SidoCodeService {
	List<SidoCodeDto> selectSido() throws Exception;

	List<SidoCodeDto> selectGugun(String sido) throws Exception;

	List<HouseInfo> selectDong(String gugun) throws Exception;

	List<HouseInfo> selectApt(String dong) throws Exception;
}
