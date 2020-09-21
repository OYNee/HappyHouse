package com.ssafy.happyhouse.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavController {

	@GetMapping(value = "/move-notice")
	public String notice() {
		return "notice";
	}
	
	@GetMapping(value = "/move-qna")
	public String qna() {
		return "qna";
	}

	@GetMapping(value = "/move-news")
	public String mvNews() {
		return "news";
	}
	
	@GetMapping(value = "/move-housedeal")
	public String mvHousedeal() {
		return "aptDeal/map";
	}

}
