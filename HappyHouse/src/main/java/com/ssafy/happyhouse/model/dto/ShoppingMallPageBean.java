package com.ssafy.happyhouse.model.dto;

import java.io.Serializable;
import java.util.Arrays;

public class ShoppingMallPageBean implements Serializable {
	private String dong;

	private int currentPage;
	private int sizePerPage;
	
	private boolean[] searchType;
	private String[] t;
	
	public ShoppingMallPageBean() {
	}
	
	public ShoppingMallPageBean(String dong, int currentPage, int sizePerPage) {
		this.dong = dong;
		this.currentPage = currentPage;
		this.sizePerPage = sizePerPage;
	}

	public ShoppingMallPageBean(String dong, int currentPage, int sizePerPage, boolean[] searchType, String[] t) {
		super();
		this.dong = dong;
		this.currentPage = currentPage;
		this.sizePerPage = sizePerPage;
		this.searchType = searchType;
		this.t = t;
	}

	public boolean[] getSearchType() {
		return searchType;
	}

	public void setSearchType(boolean[] searchType) {
		this.searchType = searchType;
	}

	public String[] getT() {
		return t;
	}

	public void setT(String[] t) {
		this.t = t;
	}

	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
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
		return "ShoppingMallPageBean [dong=" + dong + ", currentPage=" + currentPage + ", sizePerPage=" + sizePerPage
				+ ", searchType=" + Arrays.toString(searchType) + ", t=" + Arrays.toString(t) + "]";
	}
	
	
	
}
