package com.ssafy.happyhouse.model.service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ssafy.happyhouse.model.dto.AccountDto;
import com.ssafy.happyhouse.model.dto.NewsDto;

public interface MailService {
	void sendWelcomeMail(AccountDto acc, HttpServletRequest request);
	public void findPasswdMail(AccountDto acc, HttpServletRequest request);
	public void sendNewsMail(String[] userlist, List<NewsDto> news);
}
