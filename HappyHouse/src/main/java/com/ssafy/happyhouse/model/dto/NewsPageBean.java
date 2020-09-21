package com.ssafy.happyhouse.model.dto;

public class NewsPageBean {
	private String keyword;

	private int currentPage;
	private int sizePerPage;
	
	public NewsPageBean() {
	}
	
	
	public NewsPageBean(String keyword, int currentPage, int sizePerPage) {
		super();
		this.keyword = keyword;
		this.currentPage = currentPage;
		this.sizePerPage = sizePerPage;
	}


	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getSizePerPage() {
		return sizePerPage;
	}
	public void setSizePerPage(int sizePerPage) {
		this.sizePerPage = sizePerPage;
	}


	@Override
	public String toString() {
		return "NewsPageDto [keyword=" + keyword + ", currentPage=" + currentPage + ", sizePerPage=" + sizePerPage
				+ "]";
	}
	
	
}
