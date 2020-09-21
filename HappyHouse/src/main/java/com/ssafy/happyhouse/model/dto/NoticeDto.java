package com.ssafy.happyhouse.model.dto;

public class NoticeDto {
	private int id;
	private String userid;
	private String title;
	private String content;
	private String datetime;
	
	public NoticeDto() {
	}
	
	public NoticeDto(int id, String userid, String title, String content, String datetime) {
		super();
		this.id = id;
		this.userid = userid;
		this.title = title;
		this.content = content;
		this.datetime = datetime;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
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
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	@Override
	public String toString() {
		return "Notice [id=" + id + ", userid=" + userid + ", title=" + title + ", content=" + content + ", datetime="
				+ datetime + "]";
	}
	
	

}
