package com.ssafy.happyhouse.model.dto;

import java.io.Serializable;

public class ShoppingMall implements Serializable {
	private int no;
	private String shopName;
	private String code1;
	private String codename4;
	private String city;
	private String gu;
	private String dong; // 법정동
	private String jibuaddress; // 지번 주소
	private String address; // 도로명 주소
	private String lng;
	private String lat;
	
	public ShoppingMall() {
	}

	public ShoppingMall(int no, String shopName, String codename4, String city, String gu, String dong,
			String jibuaddress, String address, String lng, String lat) {
		this.no = no;
		this.shopName = shopName;
		this.codename4 = codename4;
		this.city = city;
		this.gu = gu;
		this.dong = dong;
		this.jibuaddress = jibuaddress;
		this.address = address;
		this.lng = lng;
		this.lat = lat;
	}
	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getCodename4() {
		return codename4;
	}

	public void setCodename4(String codename4) {
		this.codename4 = codename4;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getGu() {
		return gu;
	}

	public void setGu(String gu) {
		this.gu = gu;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getJibuaddress() {
		return jibuaddress;
	}

	public void setJibuaddress(String jibuaddress) {
		this.jibuaddress = jibuaddress;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	@Override
	public String toString() {
		return "ShoppingMall [no=" + no + ", shopName=" + shopName + ", codename4=" + codename4 + ", city=" + city
				+ ", gu=" + gu + ", dong=" + dong + ", jibuaddress=" + jibuaddress + ", address=" + address + ", lng="
				+ lng + ", lat=" + lat + "]";
	}

	public String getCode1() {
		return code1;
	}

	public void setCode1(String code1) {
		this.code1 = code1;
	}

	
}
