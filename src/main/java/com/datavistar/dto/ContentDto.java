package com.datavistar.dto;

public class ContentDto {

	private String title;
	private String body;
	private Integer id;
	private Integer topicId;

	public ContentDto() {
	}

	public ContentDto(String title, String body, Integer id, Integer topicId) {
		this.title = title;
		this.body = body;
		this.id = id;
		this.topicId = topicId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTopicId() {
		return topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}
}
