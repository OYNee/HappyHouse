package com.ssafy.happyhouse.model.dto;

public class QnAPageBean {
	private int currentPage;
	private int sizePerPage;
	
	public QnAPageBean() {
	}
	
	public QnAPageBean(int currentPage, int sizePerPage) {
		this.currentPage = currentPage;
		this.sizePerPage = sizePerPage;
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
		return "QnAPageBean [currentPage=" + currentPage + ", sizePerPage=" + sizePerPage + "]";
	}
	
}
