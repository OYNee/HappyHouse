package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.NewsDto;
import com.ssafy.happyhouse.model.dto.NewsPageBean;
import com.ssafy.happyhouse.model.repo.NewsDao;
import com.ssafy.happyhouse.util.NaverNewsApi;
import com.ssafy.happyhouse.util.PageNavigation;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsDao newsDao;


	@Override
	public List<NewsDto> selectAll() {
		return newsDao.selectAll();
	}

	@Override
	public List<NewsDto> search(int pg, int sizePerPage, String keyword) {
		
		NewsPageBean bean = new NewsPageBean(keyword, (pg - 1) * sizePerPage, sizePerPage);
		return newsDao.search(bean);
	}
	
	@Override
	public PageNavigation makePageNavigation(int currentPage, int sizePerPage, String keyword) throws Exception {
		PageNavigation pageNavigation = new PageNavigation();
		int naviSize = 10;// 페이지 갯수
		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		int totalCount = newsDao.getTotalCount(keyword);// 총 개시글 수(?)
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount-1) / sizePerPage;// 총 페이지수(?)
		pageNavigation.setTotalPageCount(totalPageCount+1);
		boolean startRange = currentPage <= naviSize;// startRange true:이전X, false : 이전 O
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;// endRange true:다음X, false : 다음 O
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		return pageNavigation;
	}

	
	@Scheduled(cron="0 0 10,22 * * ?")
//	@Scheduled(fixedDelay = 500000)
	public void crawlingScheduler() {
		NaverNewsApi api = new NaverNewsApi();
		try {
			List<NewsDto> list = api.parse();
			newsDao.insertList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
