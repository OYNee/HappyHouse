package com.ssafy.happyhouse.model.dto;

public class AccountDto {
	private String userid;
	private String passwd;
	private String name;
	private String addr1;
	private String addr2;
	private String email;
	private String phone;
	private MyInterestArea myInterestArea;
	private String status;

	public AccountDto() {
	}

	public AccountDto(String userid, String passwd, String name, String addr1, String addr2, String email, String phone,
			MyInterestArea myInterestArea, String status) {
		this.userid = userid;
		this.passwd = passwd;
		this.name = name;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.email = email;
		this.phone = phone;
		this.myInterestArea = myInterestArea;
		this.status = status;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public MyInterestArea getMyInterestArea() {
		return myInterestArea;
	}

	public void setMyInterestArea(MyInterestArea myInterestArea) {
		this.myInterestArea = myInterestArea;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "AccountDto [userid=" + userid + ", passwd=" + passwd + ", name=" + name + ", addr1=" + addr1
				+ ", addr2=" + addr2 + ", email=" + email + ", phone=" + phone + ", myInterestArea=" + myInterestArea
				+ ", status=" + status + "]";
	}

	
}
