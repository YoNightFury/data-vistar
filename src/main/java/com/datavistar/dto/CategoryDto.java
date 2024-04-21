package com.datavistar.dto;

public class CategoryDto {

	private String name;

	public CategoryDto(String name, Integer id) {
		super();
		this.name = name;
		this.id = id;
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

	public CategoryDto() {
		super();
	}

	private Integer id;

}
