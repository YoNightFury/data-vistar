package com.datavistar.dto;

public class CourseDto {

	private String name;
	private Integer id;
	private Integer subjectId;

	public CourseDto() {
	}

	public CourseDto(String name, Integer id, Integer subjectId) {
		this.name = name;
		this.id = id;
		this.subjectId = subjectId;
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

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}
}
