package com.ssafy.happyhouse.model.dto;

public class NewsDto {
	private String id;
	private String title;
	private String originallink;
	private String link;
	private String description;
	private String pubdate;
	
	public NewsDto() {
	}

	public NewsDto(String id, String title, String originallink, String link, String description, String pubdate) {
		super();
		this.id = id;
		this.title = title;
		this.originallink = originallink;
		this.link = link;
		this.description = description;
		this.pubdate = pubdate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOriginallink() {
		return originallink;
	}
	public void setOriginallink(String originallink) {
		this.originallink = originallink;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPubdate() {
		return pubdate;
	}
	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}

	@Override
	public String toString() {
		return "NewsDto [id=" + id + ", title=" + title + ", originallink=" + originallink + ", link=" + link
				+ ", description=" + description + ", pubdate=" + pubdate + "]";
	}
	
	
}
