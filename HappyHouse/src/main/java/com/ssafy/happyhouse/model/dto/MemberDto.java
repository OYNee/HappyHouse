package com.ssafy.happyhouse.model.dto;

//없앨 것
public class MemberDto {

	private int memberNo;
	private String memberId;
	private String memberPswd;
	private String memberName;
	private String memberAddr;
	private String memberEmail;
	private String memberPhone;

	public MemberDto() {
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPswd() {
		return memberPswd;
	}

	public void setMemberPswd(String memberPswd) {
		this.memberPswd = memberPswd;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberAddr() {
		return memberAddr;
	}

	public void setMemberAddr(String memberAddr) {
		this.memberAddr = memberAddr;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	@Override
	public String toString() {
		return "MemberDto [memberNo=" + memberNo + ", memberId=" + memberId + ", memberPswd=" + memberPswd
				+ ", memberName=" + memberName + ", memberAddr=" + memberAddr + ", memberEmail=" + memberEmail
				+ ", memberPhone=" + memberPhone + "]";
	}
	
	
}
