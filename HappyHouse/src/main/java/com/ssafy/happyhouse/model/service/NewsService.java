package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.NewsDto;
import com.ssafy.happyhouse.util.PageNavigation;

public interface NewsService {
	List<NewsDto> selectAll();
	List<NewsDto> search(int pg, int sizePerPage, String keyword);
	
	public PageNavigation makePageNavigation(int pg, int sizePerPage, String keyword) throws Exception;
//	public void crawlingScheduler();
}
