package com.ssafy.happyhouse.model.dto;

import java.util.List;

import com.ssafy.happyhouse.util.PageNavigation;

public class QnAListDto {
	private List<QnADto> list;
	PageNavigation pageNavigation;
	
	public QnAListDto() {
	}
	
	public QnAListDto(List<QnADto> list, PageNavigation pageNavigation) {
		this.list = list;
		this.pageNavigation = pageNavigation;
	}

	public List<QnADto> getList() {
		return list;
	}

	public void setList(List<QnADto> list) {
		this.list = list;
	}

	public PageNavigation getPageNavigation() {
		return pageNavigation;
	}

	public void setPageNavigation(PageNavigation pageNavigation) {
		this.pageNavigation = pageNavigation;
	}

	@Override
	public String toString() {
		return "QnAListDto [list=" + list + ", pageNavigation=" + pageNavigation + "]";
	}
	
}
