package com.ssafy.happyhouse.model.repo;

import java.util.List;

import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.dto.SidoCodeDto;

public interface SidoCodeDao {

	List<SidoCodeDto> selectSido() throws Exception;

	List<SidoCodeDto> selectGugun(String sido) throws Exception;

	List<HouseInfo> selectDong(String gugun) throws Exception;

	List<HouseInfo> selectApt(String dong) throws Exception;

	HouseInfo selectDongLatLan(String dong) throws Exception;
}
