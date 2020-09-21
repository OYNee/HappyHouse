package com.ssafy.happyhouse.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.service.BIdCheckService;


@RestController
public class BIdCheckController {
	@Autowired
	private BIdCheckService bIdCheckService;

	@PostMapping("/idCheck")
	private String IdCheck(String id_input) {
		try {
			return bIdCheckService.idCheck(id_input) +"";
		} catch (Exception e) {
			return -1+"";
		}
	}

}//class
