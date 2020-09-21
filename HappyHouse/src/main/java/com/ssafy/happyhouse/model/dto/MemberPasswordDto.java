package com.ssafy.happyhouse.model.dto;

public class MemberPasswordDto {

	private String userid;
    private String passwd;
    
    public MemberPasswordDto() {
	}
    
    public MemberPasswordDto(String userid, String passwd) {
		super();
		this.userid = userid;
		this.passwd = passwd;
	}



	public MemberPasswordDto(String passwd) {
		this.passwd = passwd;
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

	@Override
	public String toString() {
		return "MemberPasswordDto [userid=" + userid + ", passwd=" + passwd + "]";
	}



    
}
