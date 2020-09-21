package com.ssafy.happyhouse.restcontroller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.ShoppingMall;
import com.ssafy.happyhouse.model.dto.ShoppingMallPageBean;
import com.ssafy.happyhouse.model.service.AccountService;
import com.ssafy.happyhouse.model.service.ShoppingMallService;

@RestController
public class ShoppingMallRestController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private ShoppingMallService shoppingMallService;

	@GetMapping("/shoplist")
	private List<ShoppingMall> shopList(ShoppingMallPageBean shoppingMallPageBean, @RequestParam(value="shopType[]")List<String> shopType) {
		int idx = 0;
//		System.out.println(shopType.toString());

		if (shopType != null) {
			for (int i = 0; i < shopType.size(); i++) {
				idx++;
			}
			shoppingMallPageBean.setT(new String[idx]);
			for (int i = 0; i < idx; i++) {
				shoppingMallPageBean.getT()[i] = (shopType.get(i));
			}

		} else {
			shoppingMallPageBean.setT(new String[] { "D", "R", "F", "Q", "N", "L", "S", "O", "P" });
		}
		List<ShoppingMall> s = shoppingMallService.testSearchAll(shoppingMallPageBean);
		for (ShoppingMall ss : s) {
			ss.setLat(ss.getLat().replaceAll("\"", ""));
			ss.setLng(ss.getLng().replaceAll("\"", ""));
		}
		System.out.println(s.toString());
		return s;
	}

	@GetMapping("/shoplist/{no}")
	private ShoppingMall shopDetail(@RequestParam("no") String no) {

		return shoppingMallService.search(Integer.parseInt(no));
	}

}
