package com.ssafy.happyhouse.model.dto;

public class MyInterestArea {
	private String userid;
	private String dong1;
	private String dong2;
	private String dong3;
	private String mailing;
	
	public MyInterestArea() {
		// TODO Auto-generated constructor stub
	}
	

	public MyInterestArea(String userid, String dong1, String dong2, String dong3, String mailing) {
		this.userid = userid;
		this.dong1 = dong1;
		this.dong2 = dong2;
		this.dong3 = dong3;
		this.mailing = mailing;
	}


	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}


	public String getDong1() {
		return dong1;
	}


	public void setDong1(String dong1) {
		this.dong1 = dong1;
	}


	public String getDong2() {
		return dong2;
	}


	public void setDong2(String dong2) {
		this.dong2 = dong2;
	}


	public String getDong3() {
		return dong3;
	}


	public void setDong3(String dong3) {
		this.dong3 = dong3;
	}


	public String getMailing() {
		return mailing;
	}


	public void setMailing(String mailing) {
		this.mailing = mailing;
	}


	@Override
	public String toString() {
		return "MyInterestArea [userid=" + userid + ", dong1=" + dong1 + ", dong2=" + dong2 + ", dong3=" + dong3
				+ ", mailing=" + mailing + "]";
	}
	
	

}
