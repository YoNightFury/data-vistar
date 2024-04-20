package com.datavistar.dto;

public class TopicDto {

	private String name;
	private Integer id;
	private Integer courseId;

	public TopicDto() {
	}

	public TopicDto(String name, Integer id, Integer courseId) {
		this.name = name;
		this.id = id;
		this.courseId = courseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
}
