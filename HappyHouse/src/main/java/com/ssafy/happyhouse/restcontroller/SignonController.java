package com.ssafy.happyhouse.restcontroller;
// signon/off controller

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.AccountDto;
import com.ssafy.happyhouse.model.service.AccountService;

@RestController
public class SignonController {
	
	@Autowired
	private AccountService accountService;

	@PostMapping("/login")
	public int login(String mbr_id, String mbr_pwd, HttpServletRequest request) {
		AccountDto accountDto = new AccountDto();
		accountDto.setUserid(mbr_id);
		accountDto.setPasswd(mbr_pwd);
		
		int code = -1;
		try {
			System.out.println(code);
			accountDto = accountService.login(accountDto);
			
			if (accountDto == null)  {
				return code;
			} else if(!"on".equals(accountDto.getStatus())) {
				code = 0;
				return code;
			}
			
			System.out.println(accountDto.toString());
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("user_session", accountDto);
			code=1;
			return code;
		} catch (Exception e) {
			return -100;
		}
		
	}
	


}
