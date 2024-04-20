package com.datavistar.dto;

public class SubjectDto {

	private String name;
	private Integer id;
	private Integer categoryId;
	
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public SubjectDto(String name, Integer id, Integer categoryId) {
		super();
		this.name = name;
		this.id = id;
		this.categoryId = categoryId;
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
	public SubjectDto() {
		super();
	}
	
}
