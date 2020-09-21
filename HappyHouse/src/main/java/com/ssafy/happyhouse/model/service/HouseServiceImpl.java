package com.ssafy.happyhouse.model.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HousePageBean;
import com.ssafy.happyhouse.model.repo.HouseDao;
import com.ssafy.happyhouse.util.HappyHouseException;
import com.ssafy.happyhouse.util.PageNavigation;

@Service
public class HouseServiceImpl implements HouseService {
	
	@Autowired
	private HouseDao houseDao;

	public List<HouseDeal> searchAll(int pg, int sizePerPage, HousePageBean bean) {
		boolean[] types = bean.getSearchType();

		int cnt = 0;
		for (boolean t : types) {
			if (t) {
				cnt++;
			}
		}
		if (cnt == 0) {
			throw new HappyHouseException("주택타입은 반드시 한개이상을 선택해야 합니다.");
		}

		bean.setCurrentPage((pg - 1) * sizePerPage);
		bean.setSizePerPage(sizePerPage);
		return houseDao.searchAll(bean);
	}
	public HouseDeal search(int no) throws UnsupportedEncodingException {
		HouseDeal deal = houseDao.search(no);
		if (deal == null) {
			throw new HappyHouseException(String.format("거래번호 %d번에 해당하는 주택거래 정보가 존재하지 않습니다.", no));
		}

		deal.setImg(java.net.URLEncoder.encode(deal.getAptName(), "euc-kr")+".jpg");
		return deal;
	}

	
	@Override
	public PageNavigation makePageNavigation(int currentPage, int sizePerPage, HousePageBean bean) throws Exception {
		PageNavigation pageNavigation = new PageNavigation();
		int naviSize = 10;//페이지 갯수
		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		int totalCount = houseDao.getTotalCount(bean);//총 개시글 수(?)
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount-1)/sizePerPage;//총 페이지수(?)
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = currentPage<= naviSize;//startRange true:이전X, false : 이전 O
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount-1)/naviSize*naviSize<currentPage;//endRange  true:다음X, false : 다음 O 
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		return pageNavigation;
	}
	
	
	@Override
	public List<HouseDeal> testSearchAll(HousePageBean bean) {
		boolean[] types = bean.getSearchType();

		int cnt = 0;
		for (boolean t : types) {
			if (t) {
				cnt++;
			}
		}
		if (cnt == 0) {
			throw new HappyHouseException("주택타입은 반드시 한개이상을 선택해야 합니다.");
		}

		return houseDao.testSearchAll(bean);
	}
	@Override
	public List<HouseDeal> getList(HousePageBean housePageBean) {
		return houseDao.getList(housePageBean);
	}
}
