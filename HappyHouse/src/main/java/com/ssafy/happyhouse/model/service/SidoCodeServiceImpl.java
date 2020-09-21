package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.dto.SidoCodeDto;
import com.ssafy.happyhouse.model.repo.SidoCodeDao;

@Service
public class SidoCodeServiceImpl implements SidoCodeService {
	
	@Autowired
	private SidoCodeDao dao;

	@Override
	public List<SidoCodeDto> selectSido() throws Exception {
		return dao.selectSido();
	}

	@Override
	public List<SidoCodeDto> selectGugun(String sido) throws Exception {
		return dao.selectGugun(sido);
	}

	@Override
	public List<HouseInfo> selectDong(String gugun) throws Exception {
		return dao.selectDong(gugun);
	}

	@Override
	public List<HouseInfo> selectApt(String dong) throws Exception {
		
		List<HouseInfo> h = dao.selectApt(dong);
//		h.add(0, dao.selectDongLatLan(dong));
		return h;
	}
	

}
