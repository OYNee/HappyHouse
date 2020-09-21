package com.ssafy.happyhouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.happyhouse.model.dto.AccountDto;
import com.ssafy.happyhouse.model.service.AccountService;

@Controller
public class AccountController {

	private static final String MEMBER_REGISTRATION_FORM = "member/join";
	private static final String MEMBER_MODIFICATION_FORM = "member/updateMember";
	private static final String MEMBER_FINDPASSWD_FORM = "member/findpswd";
	
	@Autowired
	private AccountService accountService;

	@GetMapping("/register")
	public String registeForm() {
		return MEMBER_REGISTRATION_FORM;
	}


	@GetMapping("/update-profile")
	public String updateForm() {
		return MEMBER_MODIFICATION_FORM;
	}



	@GetMapping("/findpasswd")
	private String passwdfindForm() {
		return MEMBER_FINDPASSWD_FORM;
	}
	
	@GetMapping("/move-profile")
	private String profile() {
		return "member/memberProfile";
	}

	@GetMapping("/checkuser")
	public String key_alterConfirm(@RequestParam("user_id") String userid, @RequestParam("user_key") String key) {
		AccountDto dto = new AccountDto();
		dto.setUserid(userid);
		dto.setStatus("on");
		accountService.changeStatus(dto);

		return "index";
	}
	
	@GetMapping(value = "/move-myboard")
	public String myQuestionList() {
		return "member/myBoard";
	}
}