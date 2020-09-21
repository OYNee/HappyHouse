package com.ssafy.happyhouse.restcontroller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HousePageBean;
import com.ssafy.happyhouse.model.service.HouseService;
import com.ssafy.happyhouse.util.PageNavigation;

// RestController로 바꿀 것
@RestController
public class HouseDealRestController {

	@Autowired
	private HouseService houseService;

	// 추후 NavController로 옮길 것

	@GetMapping(value = "/housedeal/{no}")
	private HouseDeal houseDealDetail(String no) throws NumberFormatException, UnsupportedEncodingException {
		System.out.println(houseService.search(Integer.parseInt(no)).toString());
		return houseService.search(Integer.parseInt(no));

	}

	// test
	@GetMapping(value = "/housedeal")
	public List<HouseDeal> map(HousePageBean housePageBean, String[] type) {
		housePageBean.setSearchType(new boolean[] { false, false, false, false });
		int idx = 0;

		String[] ck = new String[4];
		if (type != null) {
			for (int i = 0; i < type.length; i++) {
				idx++;
				housePageBean.getSearchType()[Integer.parseInt(type[i])] = true;
				ck[Integer.parseInt(type[i])] = " checked";
			}
			housePageBean.setT(new String[idx]);
			for (int i = 0; i < idx; i++) {
				housePageBean.getT()[i] = (Integer.parseInt(type[i]) + 1) + "";
			}

		} else {
			housePageBean.setSearchType(new boolean[] { true, true, true, true });
			ck = new String[] { " checked", " checked", " checked", " checked" };
			housePageBean.setT(new String[] { "1", "2", "3", "4" });
		}

		System.out.println(houseService.testSearchAll(housePageBean).size());

		return houseService.testSearchAll(housePageBean);
	}
	
	@GetMapping(value = "/deal")
	public List<HouseDeal> list(HousePageBean housePageBean) {

		housePageBean.setDong("%"+housePageBean.getDong()+"%");
		//dong, aptname으로 검색
		return houseService.getList(housePageBean);
	}

	
}
