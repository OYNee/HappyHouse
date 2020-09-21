package com.ssafy.happyhouse.model.dto;

public class NoticePageBean {
	private String title;
	private String content;

	private int currentPage;
	private int sizePerPage;
	
	
	public NoticePageBean(String title, String content, int currentPage, int sizePerPage) {
		this.title = title;
		this.content = content;
		this.currentPage = currentPage;
		this.sizePerPage = sizePerPage;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	
	

}
