package com.ssafy.happyhouse.model.dto;

import java.util.List;

import com.ssafy.happyhouse.util.PageNavigation;

public class NoticeListDto {
	private List<NoticeDto> list;
	private String keyword;
	PageNavigation pageNavigation;
	
	public NoticeListDto() {
	}
	
	public NoticeListDto(List<NoticeDto> list, String keyword, PageNavigation pageNavigation) {
		super();
		this.list = list;
		this.keyword = keyword;
		this.pageNavigation = pageNavigation;
	}
	public List<NoticeDto> getList() {
		return list;
	}
	public void setList(List<NoticeDto> list) {
		this.list = list;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public PageNavigation getPageNavigation() {
		return pageNavigation;
	}
	public void setPageNavigation(PageNavigation pageNavigation) {
		this.pageNavigation = pageNavigation;
	}
	@Override
	public String toString() {
		return "NoticeListDto [list=" + list + ", keyword=" + keyword + ", pageNavigation=" + pageNavigation + "]";
	}
	
	

}
