package com.ssafy.happyhouse.model.repo;

import java.util.List;

import com.ssafy.happyhouse.model.dto.NewsDto;
import com.ssafy.happyhouse.model.dto.NewsPageBean;

public interface NewsDao {
	
	int insertList(List<NewsDto> list);
	int insert(NewsDto dto);
	List<NewsDto> selectAll();
	List<NewsDto> search(NewsPageBean been);
	int getTotalCount(String keyword);
	List<NewsDto> getDailyNews();
	
}
