package com.ssafy.happyhouse.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.dto.SidoCodeDto;
import com.ssafy.happyhouse.model.service.SidoCodeService;

@RestController
public class SidoCodeRestController {
	
	@Autowired
	private SidoCodeService service;
	
	@GetMapping("/sido")
	private List<SidoCodeDto> sidoList() {

		try {
			return service.selectSido();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/gugun")
	private List<SidoCodeDto> gugunList(String sido) {
		try {
			List<SidoCodeDto> l = service.selectGugun(sido);
			System.out.println(l.size());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			return service.selectGugun(sido);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/dong")
	private List<HouseInfo> dongList(String gugun) {

		try {
			return service.selectDong(gugun);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/aptlist")
	private List<HouseInfo> aptList(String dong) {

		try {
			return service.selectApt(dong);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
