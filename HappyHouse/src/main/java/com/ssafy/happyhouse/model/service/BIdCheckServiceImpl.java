package com.ssafy.happyhouse.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.repo.BIdCheckDAO;

@Service
public class BIdCheckServiceImpl implements BIdCheckService {

	@Autowired
	private BIdCheckDAO bIdCheckDAO;

	@Override
	public int idCheck(String idFromAjax) throws Exception {
		return bIdCheckDAO.idCheck(idFromAjax);
	}//idCheck

}//class
