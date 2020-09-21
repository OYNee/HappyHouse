package com.ssafy.happyhouse.restcontroller;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.AccountDto;
import com.ssafy.happyhouse.model.dto.MemberPasswordDto;
import com.ssafy.happyhouse.model.dto.MyInterestArea;
import com.ssafy.happyhouse.model.service.AccountService;
import com.ssafy.happyhouse.model.service.MailService;

@RestController
public class AccountRestController {

	@Autowired
	private AccountService accountService;
	@Autowired
	private MailService mailService;
	
	@PostMapping(value = "/register", produces = "text/plain; charset=UTF-8")
	public String register(AccountDto accountDto, MyInterestArea myInterestArea, HttpServletRequest request) {
		// 관심 동이 겹치는 경우 처리 & 첫 번째 관심동부터 설정할 수 있도록
		HashSet<String> hashSet = new HashSet<>();
		String arr[] = new String[3];
		hashSet.add(myInterestArea.getDong1());
		hashSet.add(myInterestArea.getDong2());
		hashSet.add(myInterestArea.getDong3());
		hashSet.remove("");
		Iterator<String> it = hashSet.iterator();
		int i = 0;
		while (it.hasNext()) {
			arr[i++] = it.next();
		}
		if (myInterestArea.getMailing() == null) {
			myInterestArea.setMailing("off");
		}
		accountDto.setMyInterestArea(
				new MyInterestArea(accountDto.getUserid(), arr[0], arr[1], arr[2], myInterestArea.getMailing()));

		int code = 0;
		try {
			accountDto.setStatus(userStatusKey());
			accountService.join(accountDto);
			mailService.sendWelcomeMail(accountDto, request);
			code = 1;
		} catch (Exception e) {
			e.printStackTrace();
			code = -1;
			return code + "";
		}
		return code + "";

	}

	@DeleteMapping("/deactivate")
	private String delete(HttpSession httpSession) {
		AccountDto dto = (AccountDto) httpSession.getAttribute("user_session");
		accountService.delete(dto);
		httpSession.invalidate();
		return "삭제 성공";
	}

	@PutMapping("/update-profile")
	public String updateMember(@RequestBody AccountDto accountDto, HttpSession httpSession) {
		System.out.println(accountDto.toString());
		HashSet<String> hashSet = new HashSet<>();
		String arr[] = new String[3];
		
		hashSet.add(accountDto.getMyInterestArea().getDong1());
		hashSet.add(accountDto.getMyInterestArea().getDong2());
		hashSet.add(accountDto.getMyInterestArea().getDong3());
		hashSet.remove("");
		Iterator<String> it = hashSet.iterator();
		int i = 0;
		while (it.hasNext()) {
			arr[i++] = it.next();
		}
		accountDto.getMyInterestArea().setUserid(accountDto.getUserid());
		accountDto.getMyInterestArea().setDong1(arr[0]);
		accountDto.getMyInterestArea().setDong2(arr[1]);
		accountDto.getMyInterestArea().setDong3(arr[2]);
		if (accountDto.getMyInterestArea().getMailing() == null) {
			accountDto.getMyInterestArea().setMailing("off");
		}
		accountDto.setMyInterestArea(
				new MyInterestArea(accountDto.getUserid(), arr[0], arr[1], arr[2], accountDto.getMyInterestArea().getMailing()));

		
		try {
			accountService.updateMember(accountDto);
		} catch (Exception e) {
			e.printStackTrace();
			return "문제 발생!";
		}
		httpSession.setAttribute("user_session", accountService.login(accountDto));
		return "수정되었습니다.";
	}

	@PostMapping("/findpasswd")
	private String passwdfind(AccountDto accountDto, HttpServletRequest request) {
		System.out.println(accountDto.toString());
		try {
			MemberPasswordDto res = accountService.findPswd(accountDto);
			System.out.println(res.toString());
			accountDto.setPasswd(res.getPasswd());
			mailService.findPasswdMail(accountDto, request);
			return "1";

		} catch (Exception e) {
			System.out.println("정보 없음");
			return "-1";
		}
	}

	@PostMapping("/")
	public String logout(HttpSession httpSession) {
		httpSession.invalidate();
		return "1";
	}

	@PostMapping("/passwdCheck")
	private int passwdCheck(MemberPasswordDto memberPasswordDto, HttpSession httpSession) {
		System.out.println(memberPasswordDto.toString());
		AccountDto acc = (AccountDto) httpSession.getAttribute("user_session");
		memberPasswordDto.setUserid(acc.getUserid());
		try {
			int i = accountService.passwdCheck(memberPasswordDto);
			System.out.println(i);
			 return accountService.passwdCheck(memberPasswordDto) == 1 ? 1 : -1;
		} catch (Exception e) {
			System.out.println("정보 없음");
			return 0;
		}
	}
	
	
	public String userStatusKey() {
		// 현재는 난수 5자리로
		Random r = new Random();
		String key = "";
		for (int i = 0; i < 5; i++) {
			key += r.nextInt(10) +"";
		}
		return key;
	}
	
	
}