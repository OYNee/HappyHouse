package com.ssafy.happyhouse.model.dto;

import java.util.List;

import com.ssafy.happyhouse.util.PageNavigation;

public class NewsListDto {
	private List<NewsDto> list;
	private String keyword;
	PageNavigation pageNavigation;
	
	public NewsListDto() {
	}
	
	public NewsListDto(List<NewsDto> list, String keyword, PageNavigation pageNavigation) {
		this.list = list;
		this.keyword = keyword;
		this.pageNavigation = pageNavigation;
	}

	public List<NewsDto> getList() {
		return list;
	}
	public void setList(List<NewsDto> list) {
		this.list = list;
	}
	public PageNavigation getPageNavigation() {
		return pageNavigation;
	}
	public void setPageNavigation(PageNavigation pageNavigation) {
		this.pageNavigation = pageNavigation;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "NewsListDto [list=" + list + ", keyword=" + keyword + ", pageNavigation=" + pageNavigation + "]";
	}

}
