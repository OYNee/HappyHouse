package com.ssafy.happyhouse.model.dto;

// 없앨 것
public class MemberInfoDto {
    private String userid;
    private String name;
    private String addr;
    private String email;
    private String phone;

    public MemberInfoDto() {
	}

    
	public MemberInfoDto(String userid, String name, String addr, String email, String phone) {
		super();
		this.userid = userid;
		this.name = name;
		this.addr = addr;
		this.email = email;
		this.phone = phone;
	}


	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
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

	@Override
	public String toString() {
		return "MemberInfoDto [userid=" + userid + ", name=" + name + ", addr=" + addr + ", email=" + email + ", phone="
				+ phone + "]";
	}
    
	
}
