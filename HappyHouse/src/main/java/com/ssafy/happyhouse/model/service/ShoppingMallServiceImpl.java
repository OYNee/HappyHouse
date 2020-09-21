package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.ShoppingMall;
import com.ssafy.happyhouse.model.dto.ShoppingMallPageBean;
import com.ssafy.happyhouse.model.repo.ShoppingMallDao;
import com.ssafy.happyhouse.util.PageNavigation;

@Service
public class ShoppingMallServiceImpl implements ShoppingMallService {

	@Autowired
	private ShoppingMallDao dao;

	@Override
	public List<ShoppingMall> searchAll(int currentPage, int sizePerPage, String dong) {
		ShoppingMallPageBean bean = new ShoppingMallPageBean(dong, (currentPage - 1) * sizePerPage, sizePerPage);

		return dao.searchAll(bean);

	}

	@Override
	public PageNavigation makePageNavigation(int currentPage, int sizePerPage, String dong) throws Exception {
		PageNavigation pageNavigation = new PageNavigation();
		int naviSize = 10;// 페이지 갯수
		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		int totalCount = dao.getTotalCount(dong);// 총 개시글 수(?)
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount - 1) / sizePerPage;// 총 페이지수(?)
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = currentPage <= naviSize;// startRange true:이전X, false : 이전 O
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;// endRange true:다음X, false : 다음 O
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		return pageNavigation;
	}

	@Override
	public ShoppingMall search(int num) {
		return dao.search(num);
		
	}

	@Override
	public List<ShoppingMall> testSearchAll(ShoppingMallPageBean shoppingMallPageBean) {
		return dao.testSearchAll(shoppingMallPageBean);
	}

}
