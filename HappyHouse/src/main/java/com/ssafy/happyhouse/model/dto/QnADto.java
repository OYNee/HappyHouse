package com.ssafy.happyhouse.model.dto;

public class QnADto {
	private int id; 
	private String title; 
	private String content; 
	private String userid; 
	private String datetime; 
	private String replyContent; 
	private String replyDatetime; 
	private String replyUserid;
	
	public QnADto() {
	}

	public QnADto(int id, String title, String content, String userid, String datetime, String replyContent,
			String replyDatetime, String replyUserid) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.userid = userid;
		this.datetime = datetime;
		this.replyContent = replyContent;
		this.replyDatetime = replyDatetime;
		this.replyUserid = replyUserid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getReplyDatetime() {
		return replyDatetime;
	}
	public void setReplyDatetime(String replyDatetime) {
		this.replyDatetime = replyDatetime;
	}
	public String getReplyUserid() {
		return replyUserid;
	}
	public void setReplyUserid(String replyUserid) {
		this.replyUserid = replyUserid;
	}

	@Override
	public String toString() {
		return "QnADto [id=" + id + ", title=" + title + ", content=" + content + ", userid=" + userid + ", datetime="
				+ datetime + ", replyContent=" + replyContent + ", replyDatetime=" + replyDatetime + ", replyUserid="
				+ replyUserid + "]";
	}
	
}
