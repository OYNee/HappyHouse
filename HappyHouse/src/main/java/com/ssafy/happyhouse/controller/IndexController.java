package com.ssafy.happyhouse.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	@GetMapping(value = "/")
	public String form() {
		
		return "index";
	}

}
